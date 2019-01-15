package com.suntak.cloud.haiwd.service;

import com.suntak.punch.entity.XfSum;

/**
 * @Package com.suntak.cloud.microservices.payment.service
 * @Description: 员工月度消费汇总
 * @date Dec 27, 2018 5:06:31 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface XfSumService {

	/**
	 * 根据工号和月份查询员工的就餐统计
	 * @param empno
	 * @param companycode
	 * @param month
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public XfSum findXfsumByEmpno(String empno, String companycode, String month) throws Exception;
	
}
