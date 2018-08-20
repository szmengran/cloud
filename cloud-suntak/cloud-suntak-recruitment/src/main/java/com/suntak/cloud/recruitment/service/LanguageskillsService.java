package com.suntak.cloud.recruitment.service;

import com.suntak.cloud.recruitment.entity.T_hr_languageskills;

/**
 * @Package com.suntak.cloud.recruitment.service
 * @Description: 应聘者基本信息管理服务
 * @date 2018年7月19日 上午10:02:26
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface LanguageskillsService {
	
	/**
	 * 填写语言能力
	 * @param t_hr_languageskills
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public void saveOrUpdate(T_hr_languageskills t_hr_languageskills) throws Exception;
	
	/**
	 * 根据应聘者id查找紧急联系人
	 * @param applicantid
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public T_hr_languageskills findByApplicantid(String applicantid) throws Exception;
}
