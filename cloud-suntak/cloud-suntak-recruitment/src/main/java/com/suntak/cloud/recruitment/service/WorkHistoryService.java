package com.suntak.cloud.recruitment.service;

import java.util.List;

import com.suntak.cloud.recruitment.entity.T_hr_workhistory;

/**
 * @Package com.suntak.cloud.recruitment.service
 * @Description: 应聘者基本信息管理服务
 * @date 2018年7月19日 上午10:02:26
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface WorkHistoryService {
	
	/**
	 * 填写工作经历
	 * @param t_hr_workhistory
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public void saveOrUpdate(T_hr_workhistory t_hr_workhistory) throws Exception;
	
	/**
	 * 根据应聘者id查找工作经历
	 * @param applicantid
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public List<T_hr_workhistory> findByApplicantid(String applicantid) throws Exception;
	
	/**
	 * 删除工作经历
	 * @param workhistoryid
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public void delete(Integer workhistoryid) throws Exception;
}
