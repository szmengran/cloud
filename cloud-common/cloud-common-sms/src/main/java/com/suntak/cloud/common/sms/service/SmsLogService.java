package com.suntak.cloud.common.sms.service;

import java.util.List;
import java.util.Map;

import com.suntak.common.entity.T_common_sms_log;

/**
 * @Package com.szmengran.cloud.common.sms.service
 * @Description: 短信发送服务
 * @date 2018年4月6日 下午2:12:13
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface SmsLogService {
	/**
	 * 保存短信日志信息
	 * @param t_common_sms_log
	 * @throws Exception      
	 * @return: void      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public void save(T_common_sms_log t_common_sms_log) throws Exception;
	
	/**
	 * 查找短信历史记录
	 * @param params
	 * @return
	 * @throws Exception      
	 * @return: List<T_common_sms_log>      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public List<T_common_sms_log> findByConditions(Map<String, Object> params) throws Exception;
	
}
