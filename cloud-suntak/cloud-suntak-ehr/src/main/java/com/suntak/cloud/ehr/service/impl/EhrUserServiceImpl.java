package com.suntak.cloud.ehr.service.impl;
/**
 * @Package com.suntak.cloud.ehr.service.impl
 * @Description: EHR用户的信息
 * @date 2018年4月11日 下午2:26:47
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */

import java.sql.Timestamp;
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
	
	@Override
	public Boolean updatePhone(String empcode, String phone, String id_card) throws Exception {
		String strSql = "SELECT EMPCODE FROM tb_v_rpt_emp_info where idcard=? and empcode=?";
		Object[] params1 = new Object[3];
		params1[0] = id_card;
		params1[1] = empcode;
		List<EhrUser> list = abstractDao.findBySql(EhrUser.class, strSql, params1);
		if (list != null && list.size() > 0) {
			Object[] params = new Object[3];
			params[0] = phone;
			params[1] = new Timestamp(System.currentTimeMillis());
			params[2] = empcode;
			int count = abstractDao.executeSql("update TB_STA_COMMUNICATION set c_mobile_tel=?, c_operate_time=? where c_employee_id in (select c_oid FROM tb_sta_emp where c_code=?)", params);
			if (count > 0) {
				return true;
			}
		} else {
			throw new Exception("身份证号码和员工工号不匹配！");
		}
		return false;
	}
}
