package com.suntak.cloud.oa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.oa.client
 * @Description: 授权登录
 * @date Jan 29, 2019 2:27:11 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "microservices")
public interface MicroservicesClient {
	
	@GetMapping(value = "/api/v1/microservices/getuserinfo/{code}/{secret}")
	public Response getUserInfo(@PathVariable("code") String code, @PathVariable("secret") String secret) throws Exception;
}
