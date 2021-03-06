package com.suntak.cloud.test.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.test.entity.ext.Cux_soa_ft_data_v_ext;
import com.suntak.cloud.test.service.CuxSoaFtDataVService;
import com.suntak.admin.user.exception.BusinessException;
import com.szmengran.common.orm.DBManager;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.test.service.impl
 * @Description: 资料查询服务
 * @date 2018年6月25日 下午3:13:24
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class CuxSoaFtDataVServiceImpl implements CuxSoaFtDataVService{

	@Autowired
	AbstractDao abstractDao;
	
	@Override
	public List<Cux_soa_ft_data_v_ext> findByConditions(String empcode, String status, String segment1) throws Exception {
		DBManager dbManager = null;
		try {
			dbManager = new DBManager(abstractDao.getReadDataSource());
			dbManager.openConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select b.org_id organization_id from tb_v_rpt_emp_info a, t_report_company_r_org b where a.companycode=b.companycode and empcode=?");
			List<Cux_soa_ft_data_v_ext> list = abstractDao.findBySql(dbManager, Cux_soa_ft_data_v_ext.class, sql.toString(), new Object[] {empcode});
			if (list != null && list.size() > 0) {
				Cux_soa_ft_data_v_ext cux_soa_ft_data_v_ext = list.get(0);
				String strSql = genSearchSql(status, "100", segment1);
				Object[] params = new Object[1];
				params[0] = cux_soa_ft_data_v_ext.getOrganization_id();
				List<Cux_soa_ft_data_v_ext> cux_soa_ft_data_v_extList = abstractDao.findBySql(dbManager, Cux_soa_ft_data_v_ext.class, strSql, params);
				if (cux_soa_ft_data_v_extList != null && cux_soa_ft_data_v_extList.size() > 0) {
					return cux_soa_ft_data_v_extList;
				} else {
					strSql = genSearchSql(status, "200", segment1);
					cux_soa_ft_data_v_extList = abstractDao.findBySql(dbManager, Cux_soa_ft_data_v_ext.class, strSql, params);
					if (cux_soa_ft_data_v_extList != null && cux_soa_ft_data_v_extList.size() > 0) {
						return cux_soa_ft_data_v_extList;
					} else {
						strSql = genSearchSql(status, "%", segment1);
						return abstractDao.findBySql(dbManager, Cux_soa_ft_data_v_ext.class, strSql, params);
					}
				}
			} else {
				throw new BusinessException(4000);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			dbManager.close();
		}
	}
	
	private String genSearchSql(String status, String prefix, String segment1) {
		StringBuffer strSql = new StringBuffer();
		strSql.append("select * from (")
			  .append("SELECT a.wip_entity_id,a.operation_code,pnl_qty,a.ORGANIZATION_ID,a.SEGMENT1,a.Item_Rev,a.DEPT,a.CHECK_TYPE,a.CHECK_STATUS,a.INV_DATE,")
		 	  .append(" b.empcode,b.empname,b.create_time,b.finish_time")
		 	  .append(" FROM APPS.CUX_SOA_FT_DATA_V a left join t_oa_test_create_record b on a.wip_entity_id=b.wip_entity_id")
		 	  .append(" ,T_OA_TEST_STORE c")
		 	  .append(" WHERE A.CHECK_TYPE IN ('通用','飞针')")
		 	  .append(" AND a.dept=c.name")
		 	  .append(" and a.dept in (select name from T_OA_TEST_STORE)")
		 	  .append(" AND A.ORGANIZATION_ID = ?");
		if ("progress".equalsIgnoreCase(status)) {
			strSql.append(" and b.empcode is not null and b.finish_time is null")
		 	      .append(" AND (A.CHECK_STATUS IN ('待做','待钻孔','待装', '待做资料') or A.CHECK_STATUS is null)");
		} else {
			if ("pending".equalsIgnoreCase(status)) {
				strSql.append(" AND (A.CHECK_STATUS IN ('待做','待做资料') or A.CHECK_STATUS is null)");
			} else if ("drilling".equalsIgnoreCase(status)) {
				strSql.append(" AND A.CHECK_STATUS IN ('待钻孔')");
			} else if ("installing".equalsIgnoreCase(status)) {
				strSql.append(" AND A.CHECK_STATUS IN ('待装')");
			}
			strSql.append(" and b.empcode is null");
		}
		
		if (!StringUtils.isBlank(segment1) && !"*".equals(segment1)) {
			strSql.append(" and a.segment1 like '").append(prefix).append(segment1).append("%'");
		}
		
		strSql.append(" ORDER BY c.orderby desc, A.INV_DATE").append(") where rownum <= 20");
		return strSql.toString();
	}
}
