package com.suntak.cloud.recruitment.service;

import com.suntak.cloud.recruitment.entity.T_hr_applicant;

/**
 * @Package com.suntak.cloud.recruitment.service
 * @Description: 应聘者基本信息管理服务
 * @date 2018年7月19日 上午10:02:26
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface ApplicantService {
	
	/**
	 * 填写基本资料
	 * @param t_hr_applicant
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public void insert(T_hr_applicant t_hr_applicant) throws Exception;
	
	/**
	 * 更新基本资料
	 * @param t_hr_applicant
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public int update(T_hr_applicant t_hr_applicant) throws Exception;
	
	/**
	 * 根据主键查找基本信息
	 * @param applicantid
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public T_hr_applicant findById(String applicantid) throws Exception;
	
}
