package com.suntak.cloud.recruitment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_languageskills;
import com.suntak.cloud.recruitment.service.LanguageskillsService;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: TODO
 * @date 2018年7月19日 下午2:27:43
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class LanguageskillsServiceImpl implements LanguageskillsService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public void insert(T_hr_languageskills t_hr_languageskills) throws Exception {
		abstractDao.insert(t_hr_languageskills);
	}

}
