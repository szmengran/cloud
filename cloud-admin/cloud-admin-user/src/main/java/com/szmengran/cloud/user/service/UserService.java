package com.szmengran.admin.user.service;

import com.szmengran.admin.entity.T_power_user;

public interface UserService {

	/**
	 * 保存用户信息
	 * @param t_power_user
	 * @param roleids
	 * @throws Exception      
	 * @return: void      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void save(T_power_user t_power_user, String[] roleids) throws Exception;
}
