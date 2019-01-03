package com.suntak.cloud.ehr.service;

import java.util.List;

import com.suntak.ehr.entity.EhrUser;

/**
 * @Package com.suntak.cloud.ehr.service
 * @Description: 人事信息
 * @date 2018年4月11日 下午2:20:33
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface EhrUserService {
	/**
	 * 查找用户
	 * @return
	 * @throws Exception      
	 * @return: List<EhrUser>      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<EhrUser> findByCondition(String conditions) throws Exception;
	
	/**
	 * 查找用户信息
	 * @param conditions
	 * @param params
	 * @return
	 * @throws Exception      
	 * @return: List<EhrUser>      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public List<EhrUser> findByCondition(String conditions, Object[] params) throws Exception;
	
	/**
	 * 根据身份证号码员工工号修改电话号码
	 * @param empcode
	 * @param phone
	 * @param id_card
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public Boolean updatePhone(String empcode, String phone, String id_card) throws Exception;
}
