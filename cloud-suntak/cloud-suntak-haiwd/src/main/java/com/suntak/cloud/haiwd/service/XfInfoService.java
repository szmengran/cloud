package com.suntak.cloud.haiwd.service;

import java.util.List;

import com.suntak.punch.entity.Cux_xf_info;

/**
 * @Package com.suntak.cloud.microservices.payment.service
 * @Description: 消费查询服务
 * @date Dec 25, 2018 8:57:25 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface XfInfoService {
	
	/**
	 * 查找食堂消费记录
	 * @param empcodeempno
	 * @param companycode
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public List<Cux_xf_info> findSTXFByConditions(String empno, String companycode) throws Exception;
	
	/**
	 * 查找超市消费记录
	 * @param empno
	 * @param companycode
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public List<Cux_xf_info> findCSXFByConditions(String empno, String companycode) throws Exception;
}
