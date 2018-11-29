package com.suntak.cloud.microservices.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.suntak.cloud.microservices.client.WechatClient;
import com.suntak.exception.model.Response;
import com.szmengran.utils.JwtUtil;

import io.swagger.annotations.Api;

/**
 * @Package com.suntak.cloud.microservices.controller
 * @Description: TODO
 * @date Nov 29, 2018 8:40:33 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "microservices")
@RestController
@RequestMapping(path = "/api/v1/microservices", produces = { "application/json" })
public class WechatController {


	@Value("${wechat.qy.Secret}")
	private String secret;
	
	@Autowired
	private WechatClient wechatClient;
	
	@GetMapping("/getuserinfo/{code}")
	public Response getUserInfo(@PathVariable("code") String code) throws Exception {
		Response response = wechatClient.getUserInfo(code, secret);
		if (response.getStatus() == 200) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userinfo", response.getData());
			map.put("token", JwtUtil.generateToken(new Gson().toJson(response.getData())));
			response.setData(map);
		}
		return response;
	}
}
