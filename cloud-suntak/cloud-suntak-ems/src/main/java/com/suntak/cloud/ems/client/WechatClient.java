package com.suntak.cloud.ems.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.microservices.client
 * @Description: 微信调用API
 * @date 2018年9月5日 下午1:35:12
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "cloud-suntak-wechat")
public interface WechatClient {

    /**
	 * 获取用户信息
	 * @param code
	 * @param secret
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping("/api/v1/wechat/getuserinfo/{code}/{secret}")
	public Response getUserInfo(@PathVariable("code") String code, @PathVariable("secret") String secret) throws Exception;
	
	/**
	 * 获取企业微信token
	 * @param secret
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping("/api/v1/wechat/getQYToken/{secret}")
	public Response getQYToken(@PathVariable("secret") String secret) throws Exception;
}
