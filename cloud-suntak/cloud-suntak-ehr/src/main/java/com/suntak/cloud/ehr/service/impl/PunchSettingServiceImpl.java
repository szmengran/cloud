package com.suntak.cloud.ehr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.ehr.service.PunchSettingService;
import com.suntak.punch.entity.Punch;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.ehr.service.impl
 * @Description: 未打卡用户查询
 * @date Jan 15, 2019 3:02:49 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class PunchSettingServiceImpl implements PunchSettingService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public List<Punch> findPunchSettingUser(List<Punch> punchs) throws Exception {
		StringBuilder strEmpnos = new StringBuilder();
		for (Punch punch: punchs) {
			strEmpnos.append(",'").append(punch.getEmpno()).append("'");
		}
		strEmpnos = strEmpnos.deleteCharAt(0);
		
		StringBuilder strSql = new StringBuilder();
		strSql.append("select a.empcode empno")
		      .append(" from tb_v_rpt_oa_emp_info a")
		      .append(" where a.empcode in (").append(strEmpnos).append(")")
		      .append(" and a.empcode not in (select empno from t_wechat_punch)");
		return abstractDao.findBySql(Punch.class, strSql.toString(), null);
	}

}
