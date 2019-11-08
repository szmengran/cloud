package com.suntak.mes.service;

import java.util.List;

import com.suntak.mes.entity.TMesCopperRule;

public interface CopperRuleService {

	/**
	 * 
	 * @description 插入所有规则
	 * @param list
	 * @param empCode
	 * @return
	 * @throws Exception
	 * @date Oct 31, 2019 10:37:34 AM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	Boolean insertAll(List<TMesCopperRule> list, String empCode) throws Exception;
	
	/**
	 * 
	 * @description 审核所有的规则
	 * @param empCode
	 * @return
	 * @throws Exception
	 * @date Oct 31, 2019 2:32:47 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	Boolean verifyAll(String empCode) throws Exception;
}
