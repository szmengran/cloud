package com.suntak.push.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.exception.model.Response;
import com.suntak.push.service.MiPushService;
import com.suntak.push.service.WipPushService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "工序工单停留提醒发送")
@RestController
@RequestMapping("/api/v1/push")
public class PushController {

	@Autowired
	private WipPushService wipPushService;
	
	@Autowired
	private MiPushService miPushService;
	
	/**
	 * 
	 * @description 工序工单停留提醒发送
	 * @return
	 * @date Sep 28, 2019 8:22:35 AM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@ApiOperation(value = "发送提醒", response = Response.class)
	@GetMapping("/send")
	public Response send() {
		wipPushService.send();
		miPushService.send();
		return new Response();
	}
}
