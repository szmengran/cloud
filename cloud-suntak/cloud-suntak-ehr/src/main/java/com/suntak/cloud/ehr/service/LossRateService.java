package com.suntak.cloud.ehr.service;

import java.util.List;

import com.suntak.cloud.ehr.entity.LossRate;

/**
 * @Package com.suntak.cloud.ehr.service
 * @Description: TODO
 * @date 2018年7月16日 上午10:44:43
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface LossRateService {
	
	/**
	 * 查找员工离职情况信息
	 * @param queryDate
	 * @param companycode
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public List<LossRate> findByConditions(String queryDate, String companycode) throws Exception;
}
