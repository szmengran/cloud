package com.suntak.cloud.recruitment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.recruitment.client.SmsCaptchaServiceClient;
import com.suntak.exception.model.Response;
import com.szmengran.common.entity.T_common_sms_captcha;

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
@Api(value = "recruitment")
@RestController
@RequestMapping(path = "/recruitment", produces = { "application/json" })
public class LoginController {

	@Autowired
	private SmsCaptchaServiceClient smsCaptchaServiceClient;
	
	@ApiOperation(value = "通过短信验证码进行登录", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@PostMapping("login")
	public Response loginWithPhoneAndCode(@RequestBody T_common_sms_captcha t_common_sms_code) throws Exception {
		Response response = smsCaptchaServiceClient.check(t_common_sms_code.getCaptcha(), t_common_sms_code.getPhone());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentAuthority", "admin");
		map.put("phone", t_common_sms_code.getPhone());
		response.setData(map);
		return response;
	}
	
	
}
