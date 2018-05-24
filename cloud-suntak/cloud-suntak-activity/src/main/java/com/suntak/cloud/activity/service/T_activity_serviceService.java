package com.suntak.cloud.activity.service;

import java.util.List;

import com.suntak.cloud.activity.entity.T_activity_service;

/**
 * @Package com.suntak.cloud.activity.service
 * @Description: 活动服务号
 * @date 2018年5月23日 上午9:09:56
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface T_activity_serviceService {
	
	/**
	 * 根据条件查找服务号信息
	 * @param conditions
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<T_activity_service> findByConditions(StringBuffer conditions, Object[] params) throws Exception;
	
	/**
	 * 根据活动主键查询活动信息
	 * @param service_id
	 * @return
	 * @throws Exception      
	 * @return: T_activity_service      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public T_activity_service findById(Integer service_id) throws Exception;
	
	/**
	 * 根据日期查找相关的服务号
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public List<T_activity_service> findAutoServiceItem(String date) throws Exception;
}
