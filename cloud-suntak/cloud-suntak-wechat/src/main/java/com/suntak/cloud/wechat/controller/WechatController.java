package com.suntak.cloud.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.wechat.client.WechatServiceClient;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.wechat.controller
 * @Description: TODO
 * @date 2018年9月4日 上午10:23:46
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping("/api/v1/wechat")
public class WechatController {

	@Value("${wechat.corpid}")
	private String corpid;
	
	@Value("${wechat.corpsecret.interview}")
	private String corpsecret;

	@Autowired
	private WechatServiceClient wechatServiceClient;
	
	@GetMapping("/token")
	public Response getToken() throws Exception {
		Object result = wechatServiceClient.getToken(corpid, corpsecret);
		Response response = new Response();
		response.setData(result);
		return response;
	}
	
	
}
