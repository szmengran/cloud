package com.suntak.cloud.questionnaire.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.suntak.cloud.questionnaire.client.QuestionnaireSmsServiceClient;
import com.suntak.cloud.questionnaire.entity.T_questionnaire_evaluate;
import com.suntak.cloud.questionnaire.entity.T_questionnaire_user;
import com.suntak.cloud.questionnaire.entity.ext.T_questionnaire_evaluate_ext;
import com.suntak.cloud.questionnaire.entity.other.Questionnaire;
import com.suntak.cloud.questionnaire.service.QuestionnaireService;
import com.suntak.cloud.questionnaire.service.QuestionnaireUserService;
import com.suntak.ehr.entity.Questionnaire_sms;
import com.suntak.exception.model.Response;
import com.szmengran.admin.user.exception.BusinessException;
import com.szmengran.utils.JwtUtil;
import com.szmengran.utils.RequestUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Package com.suntak.cloud.questionnaire.controller
 * @Description: 调查问卷服务
 * @date 2018年4月18日 下午3:51:27
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "questionnaire")
@RestController
@RequestMapping(path = "/api/v1/questionnaires", produces = { "application/json" })
public class QuestionnaireController {
	
	private final static Logger logger = LoggerFactory.getLogger(QuestionnaireController.class);
	
	@Autowired
	QuestionnaireService questionnaireService;
	
	@Autowired
	QuestionnaireSmsServiceClient questionnaireSmsServiceClient;
	
	@Autowired
	QuestionnaireUserService questionnaireUserService;
	
	@Autowired
	HttpServletRequest httpServletRequest;
	
	@Autowired
	HttpServletResponse httpServletResponse;
	
	@ApiOperation(value = "根据用户查询对应的被评价人", response = Response.class)
	@PostMapping("/")
	public Response updateQuestionnaire(@ModelAttribute T_questionnaire_evaluate t_questionnaire_evaluate) throws Exception {
		questionnaireService.update(t_questionnaire_evaluate);
		Response response = new Response();
		response.setData("更新成功！");
		return response;
	}
	
	@ApiOperation(value = "根据用户查询对应的评估表", response = Response.class)
	@GetMapping("/{userid}/{yearmonth}/{token}")
	public Response findQuestionnaireByConditions(@PathVariable("userid") Integer userid, @PathVariable("yearmonth") String yearmonth, @PathVariable("token") String token) throws Exception {
		String json = JwtUtil.parseToken(token);
        T_questionnaire_user t_questionnaire_user = new Gson().fromJson(json, T_questionnaire_user.class);
		if (userid != t_questionnaire_user.getUserid()) {
			throw new BusinessException(5105);
		} else if ((System.currentTimeMillis() - t_questionnaire_user.getUpdatestamp().getTime())/1000 > 24*60*60) {
			throw new BusinessException(5106);
		}
		if (StringUtils.isBlank(yearmonth.replace("*", ""))) {
			yearmonth = new SimpleDateFormat("yyyyMM").format(new Date());
		} else {
			yearmonth = yearmonth.trim().replace("-", "").substring(0, 6);
		}
		List<T_questionnaire_evaluate_ext> list = questionnaireService.findQuestionnaireByConditions(userid, yearmonth);
		Response response = new Response();
		response.setData(list);
		return response;
	}
	
	@ApiOperation(value = "生成所有用户的调查问卷", response = Response.class)
	@PostMapping("/{yearmonth}")
	public Response generateQuestionnaires(@PathVariable("yearmonth") String yearmonth) throws Exception {
		logger.info("generate all questionnaires request:{},{}", RequestUtils.getRequestRealIp(httpServletRequest),yearmonth);
		if (StringUtils.isBlank(yearmonth.replace("*", ""))) {
			yearmonth = new SimpleDateFormat("yyyyMM").format(new Date());
		} else {
			yearmonth = yearmonth.trim().replace("-", "").substring(0, 6);
		}
		List<T_questionnaire_user> list = questionnaireUserService.findAllUsers();
		Response response = new Response();
		if (list == null || list.size() == 0) {
			response.setStatus(5100);
			return response;
		}
		ExecutorService executors = Executors.newFixedThreadPool(10);
		for (int i=0; i<list.size(); i++) {
			T_questionnaire_user t_questionnaire_user = list.get(i);
			final String yearmonth1 = yearmonth;
			executors.submit(new Runnable() {
				@Override
				public void run() {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("yearmonth", yearmonth1);
					params.put("userid", t_questionnaire_user.getUserid());
					List<T_questionnaire_evaluate> evaluates;
					try {
						evaluates = questionnaireService.findByConditions(params);
						if (evaluates == null || evaluates.size() == 0) {
							questionnaireService.generateQuestionnaireByConditions(t_questionnaire_user.getUserid(), yearmonth1);
						}
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
					}
				}
			});
		}
		return response;
	}
	
	@ApiOperation(value = "用户提交调查问卷", response = Response.class)
	@PatchMapping("/users/{userid}/{yearmonth}/{token}")
	public Response updateQuestionnaires(@PathVariable("userid") Integer userid, @PathVariable("yearmonth") String yearmonth, @PathVariable("token") String token, @RequestBody T_questionnaire_evaluate[] t_questionnaire_evaluates) throws Exception {
		String json = JwtUtil.parseToken(token);
        T_questionnaire_user t_questionnaire_user = new Gson().fromJson(json, T_questionnaire_user.class);
		if (userid != t_questionnaire_user.getUserid()) {
			throw new BusinessException(5105);
		} else if ((System.currentTimeMillis() - t_questionnaire_user.getUpdatestamp().getTime())/1000 > 24*60*60) {
			throw new BusinessException(5106);
		}
		Boolean flag = questionnaireService.updateAll(userid,yearmonth, t_questionnaire_evaluates);
		if (flag) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Boolean flag = questionnaireService.check(yearmonth);
						if (flag) {
							List<Questionnaire> questionnaireList = questionnaireService.findResult(yearmonth);
							for (int i=0; i<questionnaireList.size(); i++) {
								Questionnaire questionnaire = questionnaireList.get(i);
								try {
									String year = yearmonth.substring(0,4);
									String month = yearmonth.substring(5,6);
									Questionnaire_sms questionnaire_sms = new Questionnaire_sms();
									questionnaire_sms.setName(questionnaire.getEmpname());
									questionnaire_sms.setYear(year);
									questionnaire_sms.setMonth(month);
									questionnaire_sms.setPhone(questionnaire.getPhone());
									questionnaire_sms.setNum(questionnaire.getTotalcount());
									questionnaire_sms.setScore(questionnaire.getAvgscore());
									questionnaire_sms.setRank((i+1));
									questionnaireSmsServiceClient.sendQuestionnaireSmsCode(questionnaire_sms);
								} catch (Exception e) {
									e.printStackTrace();
									logger.error(e.getMessage());
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		Response response = new Response();
		return response;
	}
	
	@ApiOperation(value="问卷调查汇总结果查询")
	@GetMapping("/result/{yearmonth}/{token}")
	public Response findResult(@PathVariable("yearmonth") String yearmonth, @PathVariable("token") String token) throws Exception {
		String json = JwtUtil.parseToken(token);
        T_questionnaire_user t_questionnaire_user = new Gson().fromJson(json, T_questionnaire_user.class);
		if (!"admin".equals(t_questionnaire_user.getEmpcode())) {
			throw new BusinessException(5105);
		} else if ((System.currentTimeMillis() - t_questionnaire_user.getUpdatestamp().getTime())/1000 > 24*60*60) {
			throw new BusinessException(5106);
		}
		List<Questionnaire> list = questionnaireService.findResult(yearmonth);
		Response response = new Response();
		response.setData(list);
		return response;
	}
}
