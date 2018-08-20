package com.suntak.cloud.recruitment.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_applicant;
import com.suntak.cloud.recruitment.service.ApplicantService;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: 应聘人员基本信息
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
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		t_hr_applicant.setCreatestamp(currentTime);
		t_hr_applicant.setUpdatestamp(currentTime);
		abstractDao.insert(t_hr_applicant);
	}

	@Override
	public int update(T_hr_applicant t_hr_applicant) throws Exception {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		t_hr_applicant.setUpdatestamp(currentTime);
		return abstractDao.update(t_hr_applicant);
	}

	@Override
	public T_hr_applicant findById(String applicantid) throws Exception {
		T_hr_applicant t_hr_applicant = new T_hr_applicant();
		t_hr_applicant.setApplicantid(applicantid);
		return abstractDao.findByPrimaryKey(t_hr_applicant);
	}

}
