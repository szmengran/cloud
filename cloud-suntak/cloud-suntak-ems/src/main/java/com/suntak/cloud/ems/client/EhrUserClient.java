package com.suntak.cloud.ems.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.microservices.client
 * @Description: EHR用户服务
 * @date Dec 25, 2018 9:22:04 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "cloud-suntak-ehr")
public interface EhrUserClient {
	
	/**
	 * 获取用户信息
	 * @param empcode
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping("/api/v1/ehr/users/{empcode}")
	Response getUserInfo(@PathVariable("empcode") String empcode) throws Exception;
	
	/**
	 * 获取企业微信的用户信息
	 * @param userid
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping("/api/v1/ehr/contact/{userid}")
	Response getContact(@PathVariable("userid") String userid) throws Exception;

	
}
