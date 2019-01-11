package com.suntak.cloud.haiwd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.haiwd.entity.Punch;
import com.suntak.cloud.haiwd.service.PunchService;
import com.suntak.cloud.haiwd.utils.DatabaseType;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;

/**
 * @Package com.suntak.cloud.haiwd.controller
 * @Description: TODO
 * @date Jan 11, 2019 11:42:33 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "haiwd")
@RestController
@RequestMapping(path = "/api/v1/haiwd", produces = { "application/json" })
public class PunchController {

	@Autowired
	private PunchService punchService;
	
	@GetMapping("/punch/{time}/{minute}/{scanSecond}")
	public Response findWorkPunch(@PathVariable("time") int time, @PathVariable("minute") int minute, @PathVariable("scanSecond") int scanSecond) throws Exception {
		List<Punch> list = punchService.findRunno(time, minute, scanSecond, DatabaseType.szdb);
		Response response = new Response();
		response.setData(list);
		return response;
	}
}
