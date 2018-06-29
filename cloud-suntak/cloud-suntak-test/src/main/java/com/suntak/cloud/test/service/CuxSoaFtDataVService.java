package com.suntak.cloud.test.service;

import java.util.List;

import com.suntak.cloud.test.entity.ext.Cux_soa_ft_data_v_ext;

/**
 * @Package com.suntak.cloud.test.service
 * @Description: 测试资料服务
 * @date 2018年6月25日 下午1:42:18
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface CuxSoaFtDataVService {
	
	/**
	 * 查询贷做资料列表
	 * @param empcode
	 * @param status
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<Cux_soa_ft_data_v_ext> findByConditions(String empcode, String status) throws Exception;
	
}
