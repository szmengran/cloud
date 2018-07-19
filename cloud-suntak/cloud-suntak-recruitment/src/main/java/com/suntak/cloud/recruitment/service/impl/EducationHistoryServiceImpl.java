package com.suntak.cloud.recruitment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_educationhistory;
import com.suntak.cloud.recruitment.service.EducationHistoryService;
import com.szmengran.common.orm.DbPrimaryKeyType;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: TODO
 * @date 2018年7月19日 下午2:24:51
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class EducationHistoryServiceImpl implements EducationHistoryService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public void insert(T_hr_educationhistory t_hr_educationhistory) throws Exception {
		abstractDao.insert(t_hr_educationhistory, DbPrimaryKeyType.SEQ, "seq_t_hr_educationhistory");
	}
}
