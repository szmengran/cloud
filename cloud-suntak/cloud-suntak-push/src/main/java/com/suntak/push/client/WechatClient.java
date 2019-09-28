package com.suntak.push.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.suntak.cloud.wechat.entity.request.NewsRequestBody;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.microservices.client
 * @Description: 微信调用API
 * @date 2018年9月5日 下午1:35:12
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "wechat", url = "https://qyapi.weixin.qq.com")
public interface WechatClient {
	
	/**
	 * 
	 * @description 机器人发送企业微信图文消息
	 * @param robotid
	 * @param newsRequestBody
	 * @return
	 * @throws Exception
	 * @date Sep 27, 2019 2:47:34 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping("/cgi-bin/webhook/send?key={robotid}")
	Response sendNews(@PathVariable("robotid") String robotid, @RequestBody NewsRequestBody newsRequestBody);
	
}
