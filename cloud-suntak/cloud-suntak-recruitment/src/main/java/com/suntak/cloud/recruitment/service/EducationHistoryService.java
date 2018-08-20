package com.suntak.cloud.recruitment.service;

import java.util.List;

import com.suntak.cloud.recruitment.entity.T_hr_educationhistory;

/**
 * @Package com.suntak.cloud.recruitment.service
 * @Description: 应聘者基本信息管理服务
 * @date 2018年7月19日 上午10:02:26
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface EducationHistoryService {
	
	/**
	 * 填写教育经历
	 * @param applicantid
	 * @param t_hr_educationhistory
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public void saveOrUpdateList(String applicantid, List<T_hr_educationhistory> t_hr_educationhistory) throws Exception;
	
	/**
	 * 保存教育经历
	 * @param applicantid
	 * @param t_hr_educationhistory
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public void saveOrUpdate(T_hr_educationhistory t_hr_educationhistory) throws Exception;
	
	/**
	 * 删除教育经历
	 * @param educationhistoryid
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public void delete(Integer educationhistoryid) throws Exception;
	
	/**
	 * 根据主键查找学习经历
	 * @param applicantid
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public List<T_hr_educationhistory> findByApplicantid(String applicantid) throws Exception;
}
