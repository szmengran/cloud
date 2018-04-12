package com.szmengran.cloud.business.sms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suntak.exception.model.Response;
import com.szmengran.common.entity.T_common_sms_log;

/**
 * @Package com.szmengran.cloud.common.sms.client
 * @Description: 人事信息调用客户端
 * @date 2018年4月12日 上午9:57:04
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "cloud-common-sms")
public interface SmsServiceClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/v1/sms")
	Response send(T_common_sms_log t_common_sms_log) throws Exception;
}
