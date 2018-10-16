package com.szmengran.cloud.common.sms.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.szmengran.cloud.common.sms.service.SmsCaptchaService;
import com.szmengran.common.entity.T_common_sms_captcha;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.szmengran.cloud.common.sms.service.impl
 * @Description: 根据条件查询验证码信息
 * @date 2018年4月19日 下午4:22:41
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class SmsCaptchaServiceImpl implements SmsCaptchaService{
	
	@Autowired
	@Qualifier("mySqlDao")
	AbstractDao abstractDao;
	
	@Override
	public void save(T_common_sms_captcha t_common_sms_captcha) throws Exception {
		t_common_sms_captcha.setUpdatestamp(new Timestamp(System.currentTimeMillis()));
		abstractDao.insert(t_common_sms_captcha);
	}

	@Override
	public T_common_sms_captcha findByPrimaryKey(T_common_sms_captcha t_common_sms_captcha) throws Exception {
		return abstractDao.findByPrimaryKey(t_common_sms_captcha);
	}

	@Override
	public int update(T_common_sms_captcha t_common_sms_captcha) throws Exception {
		t_common_sms_captcha.setUpdatestamp(new Timestamp(System.currentTimeMillis()));
		return abstractDao.update(t_common_sms_captcha);
	}

}