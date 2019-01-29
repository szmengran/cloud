package com.suntak.cloud.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.oa.client.MicroservicesClient;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;

/**
 * @Package com.suntak.cloud.oa.controller
 * @Description: TODO
 * @date Jan 29, 2019 2:51:21 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "oa")
@RestController
@RequestMapping(path="/api/v1/oa", produces = { "application/json" })
public class OauthController {
	
	@Value("${wechat.qy.Secret}")
	private String secret;
	
	@Autowired
	private MicroservicesClient microservicesClient;
	
	@GetMapping(value = "/getuserinfo/{code}")
	public Response getUserInfo(@PathVariable("code") String code) throws Exception {
		return microservicesClient.getUserInfo(code, secret);
	}
}
