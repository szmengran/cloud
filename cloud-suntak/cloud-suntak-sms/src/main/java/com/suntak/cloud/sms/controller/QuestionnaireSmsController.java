package com.suntak.cloud.sms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.sms.client.SmsServiceClient;
import com.suntak.cloud.sms.util.SmsTool;
import com.suntak.common.entity.T_common_sms_log;
import com.suntak.ehr.entity.Questionnaire_sms;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Package com.szmengran.cloud.common.sms.controller
 * @Description: 短信服务
 * @date 2018年4月6日 下午3:10:00
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "questionnaire sms")
@RestController
@RequestMapping(path = "/api/v1/suntaksms", produces = { "application/json" })
public class QuestionnaireSmsController {
	
	@Autowired
	private SmsServiceClient smsServiceClient;
	
	/**
	 * 定时发送生日祝福信息
	 * @return
	 * @throws Exception      
	 * @return: Response      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@ApiOperation(value = "发送调查问卷评估结果", response = Response.class)
	@PostMapping("/questionnaire")
	public Response sendQuestionnaireSmsCode(@RequestBody Questionnaire_sms questionnaire_sms) throws Exception {
		Response response = new Response();
		T_common_sms_log t_common_sms_log = new T_common_sms_log();
		try {
			t_common_sms_log.setTemplatecode("SMS_130924253");
			t_common_sms_log.setSignname("崇达技术");
			t_common_sms_log.setPhone(questionnaire_sms.getPhone());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", questionnaire_sms.getName());
			map.put("year", questionnaire_sms.getYear());
			map.put("month", questionnaire_sms.getMonth());
			map.put("score", questionnaire_sms.getScore());
			map.put("num", questionnaire_sms.getNum());
			map.put("rank", questionnaire_sms.getRank());
			map.put("year1", questionnaire_sms.getYear());
			map.put("month1", questionnaire_sms.getMonth());
			t_common_sms_log.setTemplateparam(SmsTool.transferMapToJson(map));
			smsServiceClient.send(t_common_sms_log);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
