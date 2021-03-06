package com.suntak.cloud.test.service;

import java.util.List;

import com.suntak.cloud.test.entity.T_oa_test_stand;
import com.suntak.cloud.test.entity.ext.T_oa_test_stand_ext;

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
	List<T_oa_test_stand> findByConditions(StringBuffer conditions, Object[] params) throws Exception;
	
	/**
	 * 查找某个工厂的所有测试架
	 * @param companyCode
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<T_oa_test_stand_ext> findByCompanyCode(String companyCode) throws Exception;
	
	/**
	 * 领用测试架
	 * @param empcode
	 * @param test_stand_code
	 * @param num
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void inuse(String empcode, String test_stand_code, Integer num) throws Exception;
	
	/**
	 * 归还测试架
	 * @param test_stand_code
	 * @param warehouse_code
	 * @param num
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void giveback(String test_stand_code, String warehouse_code, Integer num) throws Exception;
	
	/**
	 * 测试架作废
	 * @param test_stand_code
	 * @param num
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void invalid(String test_stand_code, Integer num) throws Exception;
	
	/**
	 * 保存一个测试架信息
	 * @param t_oa_test_stand
	 * @throws Exception      
	 * @return: void      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void insert(T_oa_test_stand t_oa_test_stand) throws Exception;
}
