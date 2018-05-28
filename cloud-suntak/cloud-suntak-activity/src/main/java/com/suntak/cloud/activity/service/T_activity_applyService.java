package com.suntak.cloud.activity.service;

import java.util.List;
import java.util.Map;

import com.suntak.cloud.activity.entity.T_activity_apply;
import com.suntak.cloud.activity.entity.ext.T_activity_apply_ext;

public interface T_activity_applyService{
	
	/**
	 * 按组保存活动报名信息
	 * 1）如果人数为1人，则认为单独报名
	 * 2）如果人数多于1人，则需要生成一组
	 * @param username
	 * @param service_id
	 * @param t_activity_applys
	 * @throws Exception
	 */
	public void apply(String username, Integer service_id, List<T_activity_apply> t_activity_applys) throws Exception;
	
	/**
	 * 活动签到
	 * @param username
	 * @param apply_id
	 * @return
	 * @throws Exception      
	 * @return: Boolean      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public Boolean signin(String username, Integer apply_id) throws Exception;
	
	/**
	 * 根据条件查询报名信息
	 * @param params
	 * @return
	 * @throws Exception      
	 * @return: List<T_activity_apply_ext>      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public List<T_activity_apply_ext> findByConditions(Map<String, Object> params) throws Exception;
}
