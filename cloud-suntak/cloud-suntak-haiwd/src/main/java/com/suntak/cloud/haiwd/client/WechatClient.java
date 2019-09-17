package com.suntak.cloud.haiwd.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.suntak.cloud.wechat.entity.request.TextRequestBody;
import com.suntak.cloud.wechat.entity.response.TokenResponseBody;
import com.suntak.exception.model.Response;

/**
 * 微信接口调用
 * @Package com.suntak.cloud.haiwd.client 
 * @Description: TODO
 * @date Jan 15, 2019 2:30:15 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "cloud-suntak-wechat")
public interface WechatClient {
	
	@PostMapping(value = "/api/v1/wechat/text/{secret}")
	TokenResponseBody sendText(@PathVariable("secret") String secret, @RequestBody TextRequestBody textRequestBody);
	
	/**
	 * 获取token
	 * @param secret
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/api/v1/wechat/getQYToken/{secret}")
	Response getQYToken(@PathVariable("secret") String secret) throws Exception;
}
