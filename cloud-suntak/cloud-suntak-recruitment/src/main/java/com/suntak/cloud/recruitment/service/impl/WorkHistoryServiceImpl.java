package com.suntak.cloud.recruitment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_workhistory;
import com.suntak.cloud.recruitment.service.WorkHistoryService;
import com.szmengran.common.orm.DbPrimaryKeyType;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: TODO
 * @date 2018年7月19日 下午2:36:41
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class WorkHistoryServiceImpl implements WorkHistoryService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public void insert(T_hr_workhistory t_hr_workhistory) throws Exception {
		abstractDao.insert(t_hr_workhistory, DbPrimaryKeyType.SEQ, "seq_t_hr_workhistory");
	}

}
