package com.suntak.cloud.ehr.service.impl;
/**
 * @Package com.suntak.cloud.ehr.service.impl
 * @Description: EHR用户的信息
 * @date 2018年4月11日 下午2:26:47
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.ehr.service.EhrUserService;
import com.suntak.ehr.entity.EhrUser;
import com.szmengran.common.orm.dao.AbstractDao;

@Service("ehrUserService")
public class EhrUserServiceImpl implements EhrUserService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public List<EhrUser> findByCondition(String conditions) throws Exception {
		return findByCondition(conditions, null);
	}

	@Override
	public List<EhrUser> findByCondition(String conditions, Object[] params) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT EMPCODE,EMPNAME,COMPANYCODE,COMPANYNAME,DEPTNAME,KENAME,GENDER,C_MOBILE_TEL PHONE,TO_NUMBER(TO_CHAR(SYSDATE,'yyyy'))-TO_NUMBER(to_char(labordate, 'yyyy')) year FROM tb_v_rpt_emp_info WHERE ").append(conditions);
		return abstractDao.findBySql(EhrUser.class, sql.toString(), params);
	}
}
