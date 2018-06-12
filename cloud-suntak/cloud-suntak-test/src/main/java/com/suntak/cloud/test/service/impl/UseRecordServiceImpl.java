package com.suntak.cloud.test.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import com.suntak.cloud.test.entity.T_oa_test_use_record;
import com.suntak.cloud.test.service.UseRecordService;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.test.service.impl
 * @Description: 使用记录服务
 * @date 2018年6月12日 下午5:15:02
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class UseRecordServiceImpl implements UseRecordService{

	@Autowired
	AbstractDao abstractDao;
	
	@Override
	public void insert(T_oa_test_use_record t_oa_test_use_record) throws Exception {
		abstractDao.insert(t_oa_test_use_record);
	}

	@Override
	public void updateBackTime(String test_stand_code) throws Exception {
		Object[] params = new Object[2];
		params[0] = new Timestamp(System.currentTimeMillis());
		params[1] = test_stand_code;
		abstractDao.executeSql("update t_oa_test_use_record set back_time = ? where back_time is null and test_stand_code = ?", params);
	}

}
