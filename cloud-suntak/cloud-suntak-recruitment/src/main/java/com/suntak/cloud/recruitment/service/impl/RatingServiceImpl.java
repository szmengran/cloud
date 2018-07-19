package com.suntak.cloud.recruitment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_applicant_rating;
import com.suntak.cloud.recruitment.service.RatingService;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: TODO
 * @date 2018年7月19日 下午2:29:16
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public void insert(T_hr_applicant_rating t_hr_applicant_rating) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
