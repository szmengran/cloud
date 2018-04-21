package com.szmengran.cloud.common.sms.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.suntak.exception.model.Response;
import com.szmengran.admin.user.exception.BusinessException;
import com.szmengran.cloud.common.sms.service.SmsService;
import com.szmengran.common.entity.T_common_sms_log;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Package com.szmengran.cloud.common.sms.controller
 * @Description: 短信服务
 * @date 2018年4月6日 下午3:10:00
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api("短信发送")
@RestController
@RequestMapping(path = "/api/v1", produces = { "application/json" })
public class SmsController {
	
	private final static Logger logger = LoggerFactory.getLogger(SmsController.class);
	
	@Autowired
	private SmsService smsService;
	
	@ApiOperation(value = "发送短信服务", response = Response.class)
	@PostMapping("/smses")
	public Response send(@RequestBody T_common_sms_log t_common_sms_log) throws Exception {
		logger.info("receive sms request:{}", new Gson().toJson(t_common_sms_log));
		if (StringUtils.isBlank(t_common_sms_log.getPhone())) {
			throw new BusinessException(5001);
		}
		smsService.send(t_common_sms_log);
		Response response = new Response();
		return response;
	}
	
}
