package com.suntak.cloud.recruitment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.suntak.exception.model.Response;

/**
 * @Package com.szmengran.cloud.common.sms.client
 * @Description: 人事信息调用客户端
 * @date 2018年4月12日 上午9:57:04
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "cloud-common-sms")
public interface SmsCaptchaServiceClient {

	@GetMapping("/api/v1/smses/{captcha}/{phone}")
	Response check(@PathVariable("captcha") String captcha, @PathVariable("phone") String phone) throws Exception;
}
