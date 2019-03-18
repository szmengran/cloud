package com.suntak.cloud.common.sms.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.common.sms.mapper.SmsLogMapper;
import com.suntak.cloud.common.sms.service.SmsLogService;
import com.suntak.common.entity.T_common_sms_log;

/**
 * @Package com.szmengran.cloud.common.sms.service.impl
 * @Description: 短信发送服务
 * @date 2018年4月6日 下午2:17:37
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class SmsLogServiceImpl implements SmsLogService{

	@Autowired
	private SmsLogMapper smsLogMapper;
	
	@Override
	public void save(T_common_sms_log t_common_sms_log) throws Exception {
		t_common_sms_log.setCreatestamp(new Timestamp(System.currentTimeMillis()));
		t_common_sms_log.setUpdatestamp(new Timestamp(System.currentTimeMillis()));
		smsLogMapper.insert(t_common_sms_log);
	}
	
	@Override
	public List<T_common_sms_log> findByConditions(Map<String, Object> params) throws Exception {
		return smsLogMapper.findByConditions(T_common_sms_log.class, params, null);
	}
	
}
