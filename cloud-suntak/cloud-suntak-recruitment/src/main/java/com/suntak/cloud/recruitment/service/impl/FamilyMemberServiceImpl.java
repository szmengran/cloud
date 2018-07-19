package com.suntak.cloud.recruitment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_familymember;
import com.suntak.cloud.recruitment.service.FamilyMemberService;
import com.szmengran.common.orm.DbPrimaryKeyType;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: TODO
 * @date 2018年7月19日 下午2:36:17
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class FamilyMemberServiceImpl implements FamilyMemberService{
	
	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public void insert(T_hr_familymember t_hr_familymember) throws Exception {
		abstractDao.insert(t_hr_familymember, DbPrimaryKeyType.SEQ, "seq_t_hr_familymember");
	}

}
