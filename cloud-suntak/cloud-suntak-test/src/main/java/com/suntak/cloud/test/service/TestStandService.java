package com.suntak.cloud.test.service;

import java.util.List;

import com.suntak.cloud.test.entity.T_oa_test_stand;

/**
 * @Package com.suntak.cloud.test.service
 * @Description: 测试架服务
 * @date 2018年6月11日 下午5:02:14
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface TestStandService {
	/**
	 * 查找测试架
	 * @param conditions
	 * @param params
	 * @return
	 * @throws Exception      
	 * @return: List<T_oa_test_stand>      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<T_oa_test_stand> findByConditions (StringBuffer conditions, Object[] params) throws Exception;
	
	/**
	 * 领用测试架
	 * @param empcode
	 * @param test_stand_code
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void inuse (String empcode, String test_stand_code) throws Exception;
	
	/**
	 * 归还测试架
	 * @param test_stand_code
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void giveback (String test_stand_code) throws Exception;
	
	/**
	 * 保存一个测试架信息
	 * @param t_oa_test_stand
	 * @throws Exception      
	 * @return: void      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void insert (T_oa_test_stand t_oa_test_stand) throws Exception;
}
