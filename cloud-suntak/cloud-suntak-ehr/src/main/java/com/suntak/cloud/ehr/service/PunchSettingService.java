package com.suntak.cloud.ehr.service;

import java.util.List;

import com.suntak.punch.entity.Punch;

/**
 * @Package com.suntak.cloud.ehr.service
 * @Description: TODO
 * @date Jan 15, 2019 3:02:03 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface PunchSettingService {
	
	/**
	 * 查找没有设置打卡提醒的用户
	 * @param punchs
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public List<Punch> findPunchSettingUser(List<Punch> punchs) throws Exception;
}
