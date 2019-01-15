package com.suntak.cloud.ehr.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.suntak.cloud.wechat.entity.request.TextRequestBody;
import com.suntak.cloud.wechat.entity.request.TextcardRequestBody;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.microservices.client
 * @Description: TODO
 * @date 2018年9月5日 下午1:35:12
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "cloud-suntak-wechat")
public interface WechatClient {

	@GetMapping("/api/v1/wechat/getQYToken/{secret}")
	public Response getQYToken(@PathVariable("secret") String secret) throws Exception;
	
	@PostMapping(value = "/api/v1/wechat/text/{secret}")
	public Response sendText(@PathVariable("secret") String secret, @RequestBody TextRequestBody textRequestBody);
	
	@PostMapping(value = "/api/v1/wechat/textcard/{secret}")
	public Response sendTextcard(@PathVariable("secret") String secret, @RequestBody TextcardRequestBody textcardRequestBody);
}
