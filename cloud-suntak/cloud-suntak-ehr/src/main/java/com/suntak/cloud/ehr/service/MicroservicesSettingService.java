package com.suntak.cloud.ehr.service;

import java.util.List;

import com.suntak.cloud.ehr.entity.T_microservices_setting;

/**
 * @Package com.suntak.cloud.ehr.service
 * @Description: TODO
 * @date Jan 24, 2019 11:32:56 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface MicroservicesSettingService {
	
	/**
	 * 根据工号查找用户的设置
	 * @param empno
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public List<T_microservices_setting> findSettingByEmpno (String empno) throws Exception;
	
	/**
	 * 根据类型查询设置情况
	 * @param type
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public List<String> findSettingByType(String type) throws Exception;
	
	/**
	 * 新增配置
	 * @param t_microservices_setting
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public Boolean insert(T_microservices_setting t_microservices_setting) throws Exception;
	
	/**
	 * 删除配置
	 * @param t_microservices_setting
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public Boolean delete(T_microservices_setting t_microservices_setting) throws Exception;
}
