package com.suntak.cloud.haiwd.service;

import java.util.List;

import com.suntak.cloud.haiwd.entity.Punch;
import com.suntak.cloud.haiwd.utils.DatabaseType;

/**
 * @Package com.suntak.cloud.haiwd.service
 * @Description: TODO
 * @date Jan 11, 2019 11:12:49 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface PunchService {
	
	/**
	 * 查找指定规则没有打卡的人员
	 * @param date
	 * @param yearmonth
	 * @param resultdate
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<Punch> findWorkPunch(String date, String yearmonth, String resultdate) throws Exception;
	
	/**
	 * 上班时间提前N分钟执行提醒
	 * @param time 打卡次数
	 * @param minute
	 * @param scanSecond 扫描间隔多少秒
	 * @param databaseType
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<Punch> findRunno(int time, int minute, int scanSecond, DatabaseType databaseType) throws Exception;
	
}
