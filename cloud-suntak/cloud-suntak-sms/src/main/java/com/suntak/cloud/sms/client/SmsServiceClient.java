package com.suntak.cloud.sms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.suntak.common.entity.T_common_sms_captcha;
import com.suntak.common.entity.T_common_sms_log;
import com.suntak.exception.model.Response;

/**
 * @Package com.szmengran.cloud.common.sms.client
 * @Description: 人事信息调用客户端
 * @date 2018年4月12日 上午9:57:04
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "cloud-common-sms")
public interface SmsServiceClient {
	
	@PostMapping("/api/v1/smses")
	Response send(T_common_sms_log t_common_sms_log) throws Exception;
	
	@GetMapping("/api/v1/smses/code")
    Response saveOrUpdate(@RequestBody T_common_sms_captcha t_common_sms_code) throws Exception;
    
    @GetMapping("/api/v1/smses/{code}/{phone}")
    Response check(@PathVariable("code") String code, @PathVariable("phone") String phone) throws Exception;
    
    @GetMapping("/api/v1/smses/code/{phone}")
    Response sendCode(@PathVariable("phone") String phone) throws Exception;
}
