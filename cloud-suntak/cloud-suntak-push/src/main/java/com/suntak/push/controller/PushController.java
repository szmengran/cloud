package com.suntak.push.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.exception.model.Response;
import com.suntak.push.service.PushService;

@RestController
@RequestMapping("/api/v1/push")
public class PushController {

	@Autowired
	@Qualifier("wipPushService")
	private PushService wipPushService;
	
	@Autowired
	@Qualifier("miPushService")
	private PushService miPushService;
	
	@GetMapping("/send")
	public Response send() {
		wipPushService.send();
		miPushService.send();
		return new Response();
	}
}
