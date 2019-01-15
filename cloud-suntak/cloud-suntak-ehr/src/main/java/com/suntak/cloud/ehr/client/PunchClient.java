package com.suntak.cloud.ehr.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.ehr.client
 * @Description: TODO
 * @date Jan 15, 2019 3:55:53 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "haiwd")
public interface PunchClient {
	
	@GetMapping("/api/v1/haiwd/punch/{time}/{minute}/{scanSecond}")
	public Response findPunch(@PathVariable("time") int time, @PathVariable("minute") int minute, @PathVariable("scanSecond") int scanSecond) throws Exception;
}
