package com.suntak.cloud.recruitment.service;

import java.util.List;

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
	public void saveOrUpdate(T_hr_familymember t_hr_familymember) throws Exception;
	
	/**
	 * 根据应聘者id查找家庭成员信息
	 * @param applicantid
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public List<T_hr_familymember> findByApplicantid(String applicantid) throws Exception;
	
	/**
	 * 删除家庭成员信息
	 * @param workhistoryid
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public void delete(Integer familymemberid) throws Exception;
}
