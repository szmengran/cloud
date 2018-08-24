package com.szmengran.cloud.user.service;

import java.util.List;

import com.szmengran.admin.entity.T_power_user;
import com.szmengran.admin.entity.ext.T_power_user_ext;
import com.szmengran.admin.user.exception.BusinessException;

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
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 * @throws BusinessException
	 * @throws Exception      
	 * @return: T_power_user_ext      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	T_power_user_ext login(String username, String password) throws BusinessException,Exception;
	
	/**
	 * 更新用户密码
	 * @param userid
	 * @param password
	 * @throws Exception      
	 * @return: void      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void updatePwd(String userid, String password) throws Exception;
	
	/**
	 * 根据旧密码修改新密码
	 * @param userid
	 * @param password
	 * @param oldPassword
	 * @throws Exception      
	 * @return: void      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void updatePwd(String userid, String password, String oldPassword) throws Exception;
	
	/**
	 * 根据用户角色查找用户
	 * @param assignrole
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<T_power_user> findUserByRole(String assignrole) throws Exception;
}
