package com.suntak.cloud.order.service;

public interface EngineeringOrderService {
	/**
	 * 设计工程师接单逻辑
	 * 1）设计工程师的ID回写到工程表中
	 * 2）将“等待接单”状态的工程订单修改为“设计中”
	 * @param engineering_id
	 * @param designuserid
	 * @throws Exception      
	 * @return: void      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void receive(String engineering_id, String designuserid) throws Exception;
}
