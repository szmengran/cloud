package com.suntak.cloud.sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public List<T_sms_info> findAutoSendSms() throws Exception {
		return smsInfoMapper.findAutoSendSms();
	}

	@Override
	public Boolean updateException(Integer id, String exception) throws Exception {
		return smsInfoMapper.updateException(id, exception) > 0;
	}

	@Transactional
	@Override
	public Boolean move(T_sms_info t_sms_info) throws Exception {
	    smsInfoMapper.insertIntoHistory(t_sms_info.getId());
	    smsInfoMapper.delete(t_sms_info);
        return true;
	}

    @Override
    public Boolean saveBatch(List<T_sms_info> smsInfos) throws Exception {
        return smsInfoMapper.saveBatch(smsInfos) > 0;
    }
}
