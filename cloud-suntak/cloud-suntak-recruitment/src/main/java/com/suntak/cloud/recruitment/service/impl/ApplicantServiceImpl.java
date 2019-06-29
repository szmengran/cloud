package com.suntak.cloud.recruitment.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_applicant;
import com.suntak.cloud.recruitment.mapper.ApplicantMapper;
import com.suntak.cloud.recruitment.service.ApplicantService;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: 应聘人员基本信息
 * @date 2018年7月19日 上午10:59:07
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class ApplicantServiceImpl implements ApplicantService{

	@Autowired
	private ApplicantMapper applicantMapper;
	
	@Override
	public void insert(T_hr_applicant t_hr_applicant) throws Exception {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		t_hr_applicant.setCreatestamp(currentTime);
		t_hr_applicant.setUpdatestamp(currentTime);
		t_hr_applicant.setStatus(0);
		applicantMapper.insert(t_hr_applicant);
	}

	@Override
	public int update(T_hr_applicant t_hr_applicant) throws Exception {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		t_hr_applicant.setUpdatestamp(currentTime);
		return applicantMapper.update(t_hr_applicant);
	}
	
	@Override
	public int updateBaseInfo(T_hr_applicant t_hr_applicant) throws Exception {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		t_hr_applicant.setUpdatestamp(currentTime);
		return applicantMapper.updateBaseInfo(t_hr_applicant);
	}

	@Override
	public T_hr_applicant findById(String applicantid) throws Exception {
		T_hr_applicant t_hr_applicant = new T_hr_applicant();
		t_hr_applicant.setApplicantid(applicantid);
		return applicantMapper.findById(t_hr_applicant);
	}

}
