package com.suntak.cloud.recruitment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.suntak.cloud.wechat.entity.MsgRequestBody;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.recruitment.client
 * @Description: TODO
 * @date 2018年9月5日 下午1:35:12
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "cloud-suntak-wechat")
public interface WechatServiceClient {
	
	@GetMapping("/api/v1/wechat/textcard")
	Response sendTextcard(@RequestBody MsgRequestBody msgRequestBody) throws Exception;
}