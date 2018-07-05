package com.suntak.cloud.test.service;

import com.suntak.cloud.test.entity.T_oa_test_create_record;

/**
 * @Package com.suntak.cloud.test.service
 * @Description: TODO
 * @date 2018年6月25日 下午1:55:55
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface CreateRecordService {

	/**
	 * 开始制作
	 * @param t_oa_test_create_record
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void insert(T_oa_test_create_record t_oa_test_create_record) throws Exception;
	
	/**
	 * 资料制作完成
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void finish(Integer wip_entity_id) throws Exception;
	
	/**
	 * 取消制作资料
	 * @param wip_entity_id
	 * @param empcode
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void delete(Integer wip_entity_id, String empcode) throws Exception;
	
	/**
	 * 更新测试资料的状态
	 * @param organization_id
	 * @param segment1
	 * @param item_rev
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	Boolean updateStatus(Integer organization_id, String segment1, String item_rev) throws Exception;
}
