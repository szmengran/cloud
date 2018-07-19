package com.suntak.cloud.recruitment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_applicant;
import com.suntak.cloud.recruitment.service.ApplicantService;
import com.szmengran.common.orm.DbPrimaryKeyType;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.interview.service.impl
 * @Description: TODO
 * @date 2018年7月19日 上午10:59:07
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class ApplicantServiceImpl implements ApplicantService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public void insert(T_hr_applicant t_hr_applicant) throws Exception {
		abstractDao.insert(t_hr_applicant, DbPrimaryKeyType.SEQ, "seq_t_hr_application");
	}

}
