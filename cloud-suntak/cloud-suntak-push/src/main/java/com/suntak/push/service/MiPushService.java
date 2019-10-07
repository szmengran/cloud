package com.suntak.push.service;

import java.util.List;

import com.suntak.push.entity.CuxSoaMiPush;

public interface MiPushService {

	/**
	 * 
	 * @description 发送推送通知
	 * @date Sep 27, 2019 1:37:08 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void send();
	
	/**
	 * 
	 * @description 根据序列号查询预警数据
	 * @param attribute30
	 * @return
	 * @date Sep 30, 2019 1:55:37 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<CuxSoaMiPush> findBySeq(String attribute30);
}
