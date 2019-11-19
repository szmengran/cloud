package com.suntak.cloud.questionnaire.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.suntak.cloud.wechat.entity.request.TextRequestBody;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.questionnaire.client
 * @Description: 问卷调查结果短信发送
 * @date 2018年4月21日 上午11:50:57
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "cloud-suntak-wechat")
public interface WechatClient {
	
	@PostMapping("/api/v1/wechat/text/{secret}")
	Response sendText(@PathVariable("secret") String secret, @RequestBody TextRequestBody textRequestBody) throws Exception ;
}
