package com.suntak.reserve.service;

import java.util.List;

import com.suntak.reserve.entity.TReserveRecord;

public interface RecordService {

	/**
	 * 
	 * @description 根据openid获取预约记录
	 * @param openid
	 * @return
	 * @date Nov 6, 2019 1:04:25 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<TReserveRecord> findRecordByOpenid(String openid);
	
	/**
	 * 
	 * @description 来访预约登记
	 * @param tReserveRecord
	 * @return
	 * @throws Exception
	 * @date Nov 6, 2019 4:05:10 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	Boolean insert(TReserveRecord tReserveRecord) throws Exception;
	
}
