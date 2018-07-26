package com.szmengran.cloud.common.sms.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.szmengran.cloud.common.sms.service.SmsLogService;
import com.szmengran.common.entity.T_common_sms_log;
import com.szmengran.common.orm.DbPrimaryKeyType;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.szmengran.cloud.common.sms.service.impl
 * @Description: 短信发送服务
 * @date 2018年4月6日 下午2:17:37
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class SmsLogServiceImpl implements SmsLogService{

	@Autowired
	@Qualifier("mySqlDao")
	AbstractDao abstractDao;
	
	@Override
	public void save(T_common_sms_log t_common_sms_log) throws Exception {
		t_common_sms_log.setCreatestamp(new Timestamp(System.currentTimeMillis()));
		t_common_sms_log.setUpdatestamp(new Timestamp(System.currentTimeMillis()));
		abstractDao.insert(t_common_sms_log, DbPrimaryKeyType.AUTO_INCREMENT);
	}
	
	@Override
	public List<T_common_sms_log> findByConditions(Map<String, Object> params) throws Exception {
		return abstractDao.findByConditions(T_common_sms_log.class, params);
	}
	
}
