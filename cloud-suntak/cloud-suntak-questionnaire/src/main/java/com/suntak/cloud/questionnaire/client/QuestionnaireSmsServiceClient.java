package com.suntak.cloud.questionnaire.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suntak.ehr.entity.Questionnaire_sms;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.questionnaire.client
 * @Description: 问卷调查结果短信发送
 * @date 2018年4月21日 上午11:50:57
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "cloud-suntak-sms")
public interface QuestionnaireSmsServiceClient {
	@RequestMapping(method = RequestMethod.POST, value = "/api/v1/suntaksms/questionnaire")
	Response sendQuestionnaireSmsCode(Questionnaire_sms questionnaire_sms) throws Exception;
}
