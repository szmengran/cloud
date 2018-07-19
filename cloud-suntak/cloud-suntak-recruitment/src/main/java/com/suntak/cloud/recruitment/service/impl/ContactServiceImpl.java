package com.suntak.cloud.recruitment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_contact;
import com.suntak.cloud.recruitment.service.ContactService;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: TODO
 * @date 2018年7月19日 下午2:24:20
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public void insert(T_hr_contact t_hr_contact) throws Exception {
		abstractDao.insert(t_hr_contact);
	}

}
