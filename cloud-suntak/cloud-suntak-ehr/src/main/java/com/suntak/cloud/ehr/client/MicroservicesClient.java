package com.suntak.cloud.ehr.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.ehr.client
 * @Description: TODO
 * @date Dec 17, 2018 4:37:08 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "microservices")
public interface MicroservicesClient {
	
	@GetMapping("/api/v1/microservices/getQYToken")
	Response getQYToken() throws Exception;
}
