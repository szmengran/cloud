package com.suntak.cloud.sms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.sms.client.SmsServiceClient;
import com.suntak.ehr.entity.Questionnaire_sms;
import com.suntak.exception.model.Response;
import com.szmengran.common.entity.T_common_sms_log;

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
	
	private final static Logger logger = LoggerFactory.getLogger(QuestionnaireSmsController.class);
	
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
			new Thread(new Runnable() {
				@Override
                public void run() {
					t_common_sms_log.setPhone(questionnaire_sms.getPhone());
					t_common_sms_log.setTemplateparam(
							new StringBuffer()
							.append("{")
							.append("\"name\":\"").append(questionnaire_sms.getName()).append("\"")
							.append(",\"year\":\"").append(questionnaire_sms.getYear()).append("\"")
							.append(",\"month\":\"").append(questionnaire_sms.getMonth()).append("\"")
							.append(",\"score\":\"").append(questionnaire_sms.getScore()).append("\"")
							.append(",\"num\":\"").append(questionnaire_sms.getNum()).append("\"")
							.append(",\"rank\":\"").append(questionnaire_sms.getRank()).append("\"")
							.append(",\"year1\":\"").append(questionnaire_sms.getYear()).append("\"")
							.append(",\"month1\":\"").append(questionnaire_sms.getMonth()).append("\"")
							.append("}")
							.toString()
							);
					try {
						smsServiceClient.send(t_common_sms_log);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
					}
                }
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
