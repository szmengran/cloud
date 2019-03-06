package com.suntak.cloud.sms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.sms.entity.T_sms_info;
import com.suntak.cloud.sms.mapper.SmsInfoMapper;
import com.suntak.cloud.sms.service.SmsInfoService;

/** 
 * @Package com.suntak.cloud.sms.service.impl 
 * @Description: 公共短信接口表服务
 * @date Mar 5, 2019 4:55:08 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class SmsInfoServiceImpl implements SmsInfoService {
	
	@Autowired
	private SmsInfoMapper smsInfoMapper;
	
	@Override
	public List<T_sms_info> findByConditions() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("validstatus", "1");
		return smsInfoMapper.findByConditions(T_sms_info.class, param, "createstamp desc");
	}

	@Override
	public Boolean updateStatus(Integer id, String validstatus) throws Exception {
		return smsInfoMapper.updateStatus(id, validstatus) > 0;
	}

}
