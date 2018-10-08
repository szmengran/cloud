package com.suntak.cloud.test.service;

import com.suntak.cloud.test.entity.T_oa_test_use_record;

/**
 * @Package com.suntak.cloud.test.service
 * @Description: 使用记录服务
 * @date 2018年6月12日 下午4:54:57
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface UseRecordService {
	
	/**
	 * 新增一条使用记录
	 * @param t_oa_test_use_record
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void insert (T_oa_test_use_record t_oa_test_use_record) throws Exception;
	
	/**
	 * 更新归还时间
	 * @param test_stand_code
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void updateBackTime (String test_stand_code) throws Exception;
	
	/**
	 * 查找最近的测试架操作记录
	 * @param companycode
	 * @param test_stand_code
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	T_oa_test_use_record findLastRecordByTestStandCode(String companycode, String test_stand_code) throws Exception;
	
}
