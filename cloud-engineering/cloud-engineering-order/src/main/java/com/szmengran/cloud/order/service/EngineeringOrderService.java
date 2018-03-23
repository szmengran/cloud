package com.szmengran.cloud.order.service;

public interface EngineeringOrderService {
	/**
	 * 设计工程师接单
	 * @param engineering_id
	 * @param designuserid
	 * @throws Exception      
	 * @return: void      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void receive(String engineering_id, String designuserid) throws Exception;
}
