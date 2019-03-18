package com.suntak.cloud.common.sms.service;

import com.suntak.common.entity.T_common_sms_log;

/**
 * @Package com.szmengran.cloud.common.sms.service
 * @Description: 短信发送服务
 * @date 2018年4月6日 下午2:12:13
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface SmsService {
	
	/**
	 * 短信发送
	 * @param t_common_sms_log
	 * @throws Exception      
	 * @return: void      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void send(T_common_sms_log t_common_sms_log) throws Exception;
	
}
