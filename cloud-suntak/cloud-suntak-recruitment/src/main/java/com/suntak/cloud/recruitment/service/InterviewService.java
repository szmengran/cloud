package com.suntak.cloud.recruitment.service;

import com.suntak.cloud.recruitment.entity.T_hr_interview;

/**
 * @Package com.suntak.cloud.recruitment.service
 * @Description: 应聘者基本信息管理服务
 * @date 2018年7月19日 上午10:02:26
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface InterviewService {
	
	/**
	 * 填写面试结果
	 * @param t_hr_interview
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public void insert(T_hr_interview t_hr_interview) throws Exception;
}
