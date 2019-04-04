package com.suntak.cloud.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.sms.client.EhrUserServiceClient;
import com.suntak.cloud.sms.client.SmsServiceClient;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @Package com.szmengran.cloud.common.sms.controller
 * @Description: 短信服务
 * @date 2018年4月6日 下午3:10:00
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "ehr")
@RestController
@RequestMapping(path = "/api/v1/suntaksms", produces = { "application/json" })
public class LoginSmsController {

	@Autowired
	private EhrUserServiceClient ehrUserServiceClient;
	@Autowired
	private SmsServiceClient smsServiceClient;
	
	@ApiOperation(value = "内部员工发送短信登录验证码", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@PostMapping("code/{phone}")
	public Response sendVerificationCode(@PathVariable("phone") String phone) throws Exception {
		Response response = ehrUserServiceClient.findEhrUserByPhone(phone);
		if (response.getData() != null) {
		    smsServiceClient.sendCode(phone);
		}
		return response;
	}
	
}
