package com.suntak.cloud.recruitment.service;

import com.suntak.cloud.recruitment.entity.T_hr_contact;
import com.suntak.cloud.recruitment.entity.T_hr_task;

/**
 * @Package com.suntak.cloud.recruitment.service
 * @Description: 面试任务服务
 * @date 2018年7月19日 上午10:02:26
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface TaskService {
	
	/**
	 * 添加面试任务
	 * @param t_hr_task
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public void insert(T_hr_task t_hr_task) throws Exception;
	
	/**
	 * 根据应聘者id查找紧急联系人
	 * @param applicantid
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public T_hr_task findByApplicantid(String applicantid) throws Exception;
	
	public void assignTask() throws Exception;
}
