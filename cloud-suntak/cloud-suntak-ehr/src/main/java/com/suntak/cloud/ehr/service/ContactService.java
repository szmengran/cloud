package com.suntak.cloud.ehr.service;

import com.suntak.cloud.ehr.entity.Contact;

/**
 * @Package com.suntak.cloud.ehr.service
 * @Description: TODO
 * @date Dec 18, 2018 1:28:43 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface ContactService {
	
	/**
	 * 将员工的最新消息同步到企业微信的通讯中
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void synchContact() throws Exception;
	
	/**
	 * 从企业微信中获取员工信息
	 * @param userid
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	Contact getContact(String userid) throws Exception;
	
	/**
	 * 
	 * @description 根据电话号码查找员工信息
	 * @param phone
	 * @return
	 * @date Nov 6, 2019 3:49:03 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	Contact findContactByPhone(String phone);
	
	/**
	 * 删除企业微信中已经离职的员工信息
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void deleteContact() throws Exception;
	
}
