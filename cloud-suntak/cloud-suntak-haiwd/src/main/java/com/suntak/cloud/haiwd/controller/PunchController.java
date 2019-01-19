package com.suntak.cloud.haiwd.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.haiwd.service.PunchService;
import com.suntak.cloud.haiwd.utils.DatabaseType;
import com.suntak.exception.model.Response;
import com.suntak.punch.entity.Punch;

import io.swagger.annotations.Api;

/**
 * @Package com.suntak.cloud.haiwd.controller
 * @Description: 海威达打卡服务
 * @date Jan 11, 2019 11:42:33 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "haiwd")
@RestController
@RequestMapping(path = "/api/v1/haiwd", produces = { "application/json" })
public class PunchController {

	private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(100);
	
	@Autowired
	private PunchService punchService;
	
	@GetMapping("/punch/{time}/{minute}/{scanSecond}")
	public Response findPunch(@PathVariable("time") int time, @PathVariable("minute") int minute, @PathVariable("scanSecond") int scanSecond) throws Exception {
		Future<List<Punch>> future1 = EXECUTOR.submit(() -> {
			List<Punch> list = punchService.findRunno(time, minute, scanSecond, DatabaseType.dldb);
			List<Punch> punchs = new ArrayList<Punch>();
			if (list != null && list.size() > 0) {
				punchs = punchService.findPunch(time, list, DatabaseType.dldb);
			}
			return punchs;
		});
		
		Future<List<Punch>> future2 = EXECUTOR.submit(() -> {
			List<Punch> list = punchService.findRunno(time, minute, scanSecond, DatabaseType.szdb);
			List<Punch> punchs = new ArrayList<Punch>();
			if (list != null && list.size() > 0) {
				punchs = punchService.findPunch(time, list, DatabaseType.szdb);
			}
			return punchs;
		});
		
		List<Punch> list = future2.get();
		list.addAll(future1.get());
		Set<String> set = new HashSet<String>();
		for (Punch punch: list) {
			set.add(punch.getEmpno());
		}
		
		Response response = new Response();
		response.setData(set);
		return response;
	}
}
