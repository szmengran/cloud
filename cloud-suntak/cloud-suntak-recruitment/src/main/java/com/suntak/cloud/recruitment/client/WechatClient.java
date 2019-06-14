package com.suntak.cloud.recruitment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.suntak.cloud.wechat.entity.request.TextcardRequestBody;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.recruitment.client
 * @Description: 微信API对接
 * @date 2018年9月5日 下午1:35:12
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "cloud-suntak-wechat")
public interface WechatClient {
	
	@PostMapping("/api/v1/wechat/textcard/{secret}")
	Response sendTextcard(@PathVariable("secret") String secret, @RequestBody TextcardRequestBody textcardRequestBody) throws Exception;
	
	@GetMapping("/api/v1/wechat/getuserinfo/{code}/{secret}")
	Response getUserinfo(@PathVariable("code") String code, @PathVariable("secret") String secret) throws Exception;
}
