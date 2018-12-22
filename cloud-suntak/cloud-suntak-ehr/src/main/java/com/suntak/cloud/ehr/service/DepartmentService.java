package com.suntak.cloud.ehr.service;

import com.suntak.cloud.ehr.entity.T_wechat_department;

/**
 * @Package com.suntak.cloud.ehr.service
 * @Description: TODO
 * @date Dec 17, 2018 11:17:37 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface DepartmentService {
	
	/**
	 * 新增部门
	 * @param t_wechat_department
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public void insert(T_wechat_department t_wechat_department) throws Exception;
	
	/**
	 * 更新部门
	 * @param t_wechat_department
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public void update(T_wechat_department t_wechat_department) throws Exception;
}
