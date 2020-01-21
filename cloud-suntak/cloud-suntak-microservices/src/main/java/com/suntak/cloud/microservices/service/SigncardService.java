package com.suntak.cloud.microservices.service;

import com.suntak.cloud.microservices.entity.Signcard;

public interface SigncardService {

	/**
	 * 
	 * @description 签卡申请
	 * @param signcard
	 * @return
	 * @throws Exception
	 * @date Jan 21, 2020 11:34:34 AM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	Boolean insert(Signcard signcard) throws Exception;
	
	/**
	 * 
	 * @description 更新签卡状态
	 * @param signcard
	 * @return
	 * @date Jan 21, 2020 11:11:54 AM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	Boolean updateStatus(Signcard signcard);
}
