package com.suntak.cloud.ehr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.ehr.entity.LossRate;
import com.suntak.cloud.ehr.service.LossRateService;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.ehr.service.impl
 * @Description: 员工离职情况查询服务
 * @date 2018年7月16日 上午10:48:03
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service("lossRateService")
public class LossRateServiceImpl implements LossRateService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public List<LossRate> findByConditions(String queryDate, String companycode) throws Exception{
		String startmonth = queryDate.substring(0, 8)+"01";
		Object[] params = new Object[4];
		params[0] = queryDate;
		params[1] = queryDate;
		params[2] = companycode;
		params[3] = queryDate;
		StringBuffer strSql = new StringBuffer();
		strSql.append("SELECT a.DEPTNAME,a.KENAME,")
		      .append(" count(1) startmonth,")
		      .append(" sum(case when a.EXITDATE is null then 1")
		      .append(" when a.EXITDATE >= ")
		      .append(" ? then 1")
		      .append(" else 0 end")
		      .append(" ) afterdaymonth,")
		      .append(" sum(case when a.EXITDATE BETWEEN ")
		      .append("'").append(startmonth).append("'")
		      .append(" and ? then 1")
		      .append(" else 0 end")
		      .append(" ) betweendaymonth")
		      .append(" FROM Tb_v_Rpt_Emp_Info a where")
		      .append(" (a.EXITDATE is null or a.EXITDATE >= ")
		      .append("'").append(startmonth).append("')")
		      .append(" and a.companycode=?")
		      .append(" and a.LABORDATE<=to_date(?,'yyyy-MM-dd')")
		      .append(" GROUP BY a.DEPTNAME,a.KENAME")
		    	  .append(" order BY a.DEPTNAME,a.KENAME");
		return abstractDao.findBySql(LossRate.class, strSql.toString(), params);
	}

}
