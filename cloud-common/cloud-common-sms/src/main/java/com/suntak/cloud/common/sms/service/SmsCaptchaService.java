package com.suntak.cloud.common.sms.service;

import com.suntak.common.entity.T_common_sms_captcha;

/**
 * @Package com.szmengran.cloud.common.sms.service
 * @Description: 短信验证码服务
 * @date 2018年4月19日 下午4:22:12
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface SmsCaptchaService {

	/**
	 * 保存短信记录
	 * @param t_common_sms_captcha
	 * @throws Exception      
	 * @return: void      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void save(T_common_sms_captcha t_common_sms_captcha) throws Exception;
	
	/**
	 * 根据主键查询验证码信息
	 * @param t_common_sms_captcha
	 * @return
	 * @throws Exception      
	 * @return: T_common_sms_captcha 
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	T_common_sms_captcha findByPrimaryKey(T_common_sms_captcha t_common_sms_captcha) throws Exception;
	
	/**
	 * 更新短信验证码信息
	 * @param t_common_sms_captcha
	 * @throws Exception      
	 * @return: int      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	int update(T_common_sms_captcha t_common_sms_captcha) throws Exception;
}
