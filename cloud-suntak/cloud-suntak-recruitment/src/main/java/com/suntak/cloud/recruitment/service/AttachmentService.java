package com.suntak.cloud.recruitment.service;

import com.suntak.cloud.recruitment.entity.T_hr_attachment;

/**
 * @Package com.suntak.cloud.recruitment.service
 * @Description: 应聘者基本信息管理服务
 * @date 2018年7月19日 上午10:02:26
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface AttachmentService {

	/**
	 * 上传附件
	 * @param t_hr_attachment
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	Boolean insert(T_hr_attachment t_hr_attachment) throws Exception;
	
	/**
	 * 更新附件
	 * @param t_hr_attachment
	 * @return
	 * @throws Exception      
	 * @return: Boolean      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	Boolean update(T_hr_attachment t_hr_attachment) throws Exception;
	
	/**
	 * 根据ID查找附件信息
	 * @param applicantid
	 * @return      
	 * @return: T_hr_attachment      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	T_hr_attachment findById(String applicantid) throws Exception; 
}
