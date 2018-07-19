package com.suntak.cloud.recruitment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_interview;
import com.suntak.cloud.recruitment.service.InterviewService;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: TODO
 * @date 2018年7月19日 下午2:26:50
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class InterviewServiceImpl implements InterviewService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public void insert(T_hr_interview t_hr_interview) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
