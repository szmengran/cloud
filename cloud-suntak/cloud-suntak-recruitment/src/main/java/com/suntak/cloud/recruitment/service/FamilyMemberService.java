package com.suntak.cloud.recruitment.service;

import com.suntak.cloud.recruitment.entity.T_hr_familymember;

/**
 * @Package com.suntak.cloud.interview.service
 * @Description: 应聘者基本信息管理服务
 * @date 2018年7月19日 上午10:02:26
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface FamilyMemberService {
	
	/**
	 * 填写家庭成员信息
	 * @param t_hr_familymember
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public void insert(T_hr_familymember t_hr_familymember) throws Exception;
}
