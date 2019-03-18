package com.suntak.cloud.common.sms.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.common.sms.mapper.SmsCaptchaMapper;
import com.suntak.cloud.common.sms.service.SmsCaptchaService;
import com.suntak.common.entity.T_common_sms_captcha;

/**
 * @Package com.szmengran.cloud.common.sms.service.impl
 * @Description: 根据条件查询验证码信息
 * @date 2018年4月19日 下午4:22:41
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class SmsCaptchaServiceImpl implements SmsCaptchaService{
	
	@Autowired
	private SmsCaptchaMapper smsCaptchaMapper;
	
	@Override
	public void save(T_common_sms_captcha t_common_sms_captcha) throws Exception {
		t_common_sms_captcha.setUpdatestamp(new Timestamp(System.currentTimeMillis()));
		smsCaptchaMapper.insert(t_common_sms_captcha);
	}

	@Override
	public T_common_sms_captcha findByPrimaryKey(T_common_sms_captcha t_common_sms_captcha) throws Exception {
		return smsCaptchaMapper.findById(t_common_sms_captcha);
	}

	@Override
	public int update(T_common_sms_captcha t_common_sms_captcha) throws Exception {
		t_common_sms_captcha.setUpdatestamp(new Timestamp(System.currentTimeMillis()));
		return smsCaptchaMapper.update(t_common_sms_captcha);
	}

}
