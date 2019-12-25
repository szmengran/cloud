package com.suntak.api.wechat;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.suntak.cloud.wechat.entity.request.NewsRequestBody;
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
	 * 
	 * @description 企业微信机器人发送图文消息
	 * @param robotid
	 * @param newsRequestBody
	 * @return
	 * @throws Exception
	 * @date Dec 25, 2019 9:28:42 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	Response sendRobotNews(@PathVariable("robotid") String robotid, @RequestBody NewsRequestBody newsRequestBody) throws Exception;
}
