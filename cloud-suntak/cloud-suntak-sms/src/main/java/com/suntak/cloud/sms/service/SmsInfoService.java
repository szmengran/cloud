package com.suntak.cloud.sms.service;

import java.util.List;

import com.suntak.cloud.sms.entity.T_sms_info;

/** 
 * @Package com.suntak.cloud.sms 
 * @Description: 短信服务
 * @date Mar 5, 2019 4:50:21 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface SmsInfoService {

	/**
	 * 查找所有要发送的短信
	 * @return
	 * @throws Exception      
	 * @return: List<T_sms_info>      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<T_sms_info> findAutoSendSms() throws Exception;
	
	/**
	 * 更新短信的状态
	 * @param id
	 * @param exception
	 * @return
	 * @throws Exception      
	 * @return: Boolean      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	Boolean updateException(Integer id, String exception) throws Exception;
	
	/**
	 * 发送成功的短信将转移到历史表中
	 * @param t_sms_info
	 * @return
	 * @throws Exception      
	 * @return: Boolean      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
    Boolean move(T_sms_info t_sms_info) throws Exception;
}
