package com.suntak.cloud.activity.service;

import java.util.List;

import com.suntak.cloud.activity.entity.T_activity_apply;

public interface T_activity_applyService{
	
	/**
	 * 按组保存活动报名信息
	 * 1）如果人数为1人，则认为单独报名
	 * 2）如果人数多于1人，则需要生成一组
	 * @param service_id
	 * @param t_activity_applys
	 * @throws Exception
	 */
	public void apply(Integer service_id, List<T_activity_apply> t_activity_applys) throws Exception;
}
