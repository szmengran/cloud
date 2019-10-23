package com.suntak.mes.erp.service;

import com.suntak.mes.erp.Report;

public interface ReportService {

	/**
	 * 
	 * @description 根据工单号查找工单信息
	 * @param wipEntityName
	 * @param orgId
	 * @return
	 * @throws Exception
	 * @date Oct 22, 2019 3:30:02 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	Report findReportByWipEntityName(String wipEntityName, Integer orgId) throws Exception;
	
	/**
	 * 
	 * @description 根据生产型号查找工单信息
	 * @param primaryItem
	 * @param orgId
	 * @return
	 * @throws Exception
	 * @date Oct 22, 2019 3:30:29 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	Report findReportByPrimaryItem(String primaryItem, Integer orgId) throws Exception;
}
