package com.suntak.cloud.haiwd.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.haiwd.entity.CheckindataRequest;
import com.suntak.cloud.haiwd.service.CheckindataService;
import com.suntak.exception.model.Response;

@RestController
@RequestMapping(path = "/api/v1/haiwd", produces = { "application/json" })
public class QyWechatController {

	@Value("${wechat.qy.Secret}")
	private String secret;
	
	@Autowired
	private CheckindataService checkindataService;
	
	@PostMapping("/sync")
	public Response sync(@RequestBody CheckindataRequest checkindataRequest) throws Exception {
		if (checkindataRequest.getStarttime() == null || checkindataRequest.getEndtime() == null) {
			Calendar starttime = Calendar.getInstance();
			starttime.add(Calendar.DATE, -1);
			checkindataRequest.setStarttime(starttime.getTimeInMillis() / 1000);
			Calendar emdtime = Calendar.getInstance();
			checkindataRequest.setEndtime(emdtime.getTimeInMillis() / 1000);
		}
		checkindataService.load(checkindataRequest);
		return new Response();
	}
}
