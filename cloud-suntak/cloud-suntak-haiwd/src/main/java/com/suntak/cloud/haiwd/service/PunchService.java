package com.suntak.cloud.haiwd.service;

import java.util.List;

import com.suntak.cloud.haiwd.utils.DatabaseType;
import com.suntak.punch.entity.Punch;

/**
 * @Package com.suntak.cloud.haiwd.service
 * @Description: TODO
 * @date Jan 11, 2019 11:12:49 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface PunchService {
	
	/**
	 * 查找指定规则没有打卡的人员
	 * @param time
	 * @param list
	 * @param databaseType
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<Punch> findPunch(int time, List<Punch> list, DatabaseType databaseType) throws Exception;
	
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
