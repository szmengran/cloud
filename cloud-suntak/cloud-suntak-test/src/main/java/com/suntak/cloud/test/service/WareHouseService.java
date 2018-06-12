package com.suntak.cloud.test.service;

import java.util.List;

import com.suntak.cloud.test.entity.T_oa_test_warehouse;

/**
 * @Package com.suntak.cloud.test.service
 * @Description: 库位服务
 * @date 2018年6月12日 上午10:42:51
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface WareHouseService {
	
	/**
	 * 插入一条库位信息
	 * @param t_oa_test_warehouse
	 * @throws Exception      
	 * @return: void      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void insert (T_oa_test_warehouse t_oa_test_warehouse) throws Exception;
	
	/**
	 * 库位信息查询
	 * @param conditions
	 * @param params
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<T_oa_test_warehouse> findByConditions (StringBuffer conditions, Object[] params) throws Exception;
	
	/**
	 * 库位信息删除
	 * @param warehouse_code
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void delete (String warehouse_code) throws Exception;
	
}
