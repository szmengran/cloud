package com.suntak.cloud.microservices.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.suntak.cloud.wechat.entity.request.TextRequestBody;
import com.suntak.cloud.wechat.entity.request.TextcardRequestBody;
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
	 * 发送企业微信卡片消息
	 * @param secret
	 * @param textcardRequestBody
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping("/api/v1/wechat/textcard/{secret}")
	Response sendTextcard(@PathVariable("secret") String secret, @RequestBody TextcardRequestBody textcardRequestBody) throws Exception;
	
	/**
	 * 发送文本消息
	 * @param secret
	 * @param textRequestBody
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping("/api/v1/wechat/text/{secret}")
	Response sendText(@PathVariable("secret") String secret, @RequestBody TextRequestBody textRequestBody) throws Exception;
	
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
