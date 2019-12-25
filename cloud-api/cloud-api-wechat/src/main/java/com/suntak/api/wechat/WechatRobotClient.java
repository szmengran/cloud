package com.suntak.api.wechat;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.suntak.cloud.wechat.entity.request.NewsRequestBody;
import com.suntak.cloud.wechat.entity.response.RobotResponse;

@FeignClient(name = "wechat", url = "https://qyapi.weixin.qq.com")
public interface WechatRobotClient {
	
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
	RobotResponse sendRobotNews(@PathVariable("robotid") String robotid, @RequestBody NewsRequestBody newsRequestBody);
	
}