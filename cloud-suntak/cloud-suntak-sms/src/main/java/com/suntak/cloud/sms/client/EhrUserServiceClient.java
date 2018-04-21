package com.suntak.cloud.sms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suntak.exception.model.Response;

/**
 * @Package com.szmengran.cloud.common.sms.client
 * @Description: 人事信息调用客户端
 * @date 2018年4月12日 上午9:57:04
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "cloud-suntak-ehr")
public interface EhrUserServiceClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/ehr/birthdayusers/{monthdate}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Response getBirthdayEhrUser(@PathVariable("monthdate") String monthdate);
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/ehr/onboardusers/{monthdate}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Response getOnboardEhrUser(@PathVariable("monthdate") String monthdate);
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/ehr/user/{phone}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Response findEhrUserByPhone(@PathVariable("phone") String phone);
}
