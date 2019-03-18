package com.suntak.cloud.questionnaire.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.suntak.admin.user.exception.BusinessException;
import com.suntak.cloud.questionnaire.entity.T_questionnaire_user;
import com.suntak.cloud.questionnaire.service.QuestionnaireService;
import com.suntak.cloud.questionnaire.service.QuestionnaireUserService;
import com.suntak.exception.model.Response;
import com.suntak.utils.JwtUtil;
import com.suntak.utils.RequestUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Package com.suntak.cloud.questionnaire.controller
 * @Description: 调查问卷用户服务
 * @date 2018年4月18日 下午3:51:27
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "questionnaire user")
@RestController
@RequestMapping(path = "/api/v1/questionnaires", produces = { "application/json" })
public class QuestionnaireUserController {
	
	private final static Logger logger = LoggerFactory.getLogger(QuestionnaireUserController.class);
	
	@Autowired
	QuestionnaireService questionnaireService;
	
	@Autowired
	QuestionnaireUserService questionnaireUserService;
	
	@Autowired
	HttpServletRequest httpServletRequest;
	
	@Autowired
	HttpServletResponse httpServletResponse;
	
	@ApiOperation(value = "根据用户查询对应的被评价人", response = Response.class)
	@PostMapping("/login")
	public Response login(@ModelAttribute T_questionnaire_user t_questionnaire_user) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("empcode", t_questionnaire_user.getEmpcode());
		params.put("password", t_questionnaire_user.getPassword());
		List<T_questionnaire_user> list = questionnaireUserService.findByConditions(params);
		if (list != null && list.size() > 0) {
			t_questionnaire_user = list.get(0);
			if (!"1".equals(t_questionnaire_user.getValidstatus())) {
				throw new BusinessException(5108);
			}
		} else {
			throw new BusinessException(5104);
		}
		t_questionnaire_user.setUpdatestamp(new Timestamp(System.currentTimeMillis()));
		String token = JwtUtil.generateToken(new Gson().toJson(t_questionnaire_user));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		map.put("empname", t_questionnaire_user.getEmpname());
		map.put("userid", t_questionnaire_user.getUserid());
		Response response = new Response();
		response.setData(map);
		return response;
	}
	
	@PatchMapping("/users/{userid}/{password}/{password3}/{token}")
	public Response updatePwd(@PathVariable("userid") Integer userid, @PathVariable("password") String password, @PathVariable("password3") String password3, @PathVariable("token") String token) throws Exception {
		logger.info("update password:{}{}", RequestUtils.getRequestRealIp(httpServletRequest), userid);
		String json = JwtUtil.parseToken(token);
        T_questionnaire_user t_questionnaire_user = new Gson().fromJson(json, T_questionnaire_user.class);
        if (userid != t_questionnaire_user.getUserid()) {
			throw new BusinessException(5105);
		} else if ((System.currentTimeMillis() - t_questionnaire_user.getUpdatestamp().getTime())/1000 > 24*60*60) {
			throw new BusinessException(5106);
		}
        questionnaireUserService.updatePwd(userid, password, password3);
        Response response = new Response();
		return response;
	}
}
