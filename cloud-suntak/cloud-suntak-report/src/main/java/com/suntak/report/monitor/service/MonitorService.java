package com.suntak.report.monitor.service;

import java.util.List;

import com.suntak.report.monitor.entity.T_report_monitor;

public interface MonitorService {
	/**
	 * 查找报表产能监控数据
	 * @param conditions
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<T_report_monitor> findByConditions(String key, StringBuffer conditions,Object[] params) throws Exception;
	
	/**
	 * 根据日期分析从当月1号到现在的平均效率
	 * @param date
	 * @param org_id
	 * @return
	 * @throws Exception
	 */
	public List<T_report_monitor> findYearMonthWorktime(String key, String date, Integer org_id) throws Exception;
	
	/**
	 * 根据日期分析从当天的平均效率
	 * @param date
	 * @param org_id
	 * @return
	 * @throws Exception
	 */
	public List<T_report_monitor> findDayWorktime(String key, String date, Integer org_id) throws Exception;
	
	/**
	 * 调用存储过程提取数据
	 * @param calculation_date
	 * @param calculation_time
	 * @param count
	 * @throws Exception
	 */
	public void getWorktimeMonitorData(String calculation_date, String calculation_time, Integer count) throws Exception;
}
