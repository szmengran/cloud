package com.suntak.cloud.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.test.entity.Cux_mi_checkmt_v;
import com.suntak.cloud.test.service.Cux_mi_checkmt_vService;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.test.service.impl
 * @Description: 冶具服务
 * @date 2018年10月16日 上午11:18:13
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class Cux_mi_checkmt_vServiceImpl implements Cux_mi_checkmt_vService{

	@Autowired
	AbstractDao abstractDao;
	
	@Override
	public Cux_mi_checkmt_v findByConditions(String companycode, String item) throws Exception {
		String strSql = genSearchSql("100", item);
		Object[] params = new Object[1];
		params[0] = companycode;
		List<Cux_mi_checkmt_v> list = abstractDao.findBySql(Cux_mi_checkmt_v.class, strSql, params);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			strSql = genSearchSql("200", item);
			list = abstractDao.findBySql(Cux_mi_checkmt_v.class, strSql, params);
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else {
				strSql = genSearchSql("%", item);
				list = abstractDao.findBySql(Cux_mi_checkmt_v.class, strSql, params);
				if (list != null && list.size() > 0) {
					return list.get(0);
				} else {
					return null;
				}
			}
		}
	}
	
	private String genSearchSql(String prefix, String item) {
		StringBuffer strSql = new StringBuffer();
		strSql.append("select * from (")
		      .append("SELECT a.organization_Id,a.Item_Rev,")
		      .append("a.CHECK_TYPE,a.CHECK_STATUS,a.CHECK_STRU,a.MAKE_DATE,a.CHECK_MEMO,a.ATTRIBUTE1")
		      .append(" from apps.CUX_MI_CHECKMT_V a, t_report_company_r_org b")
		      .append(" where a.organization_Id=b.org_id ")
		      .append(" and b.companycode = ?")
		      .append(" and a.item like '").append(prefix).append(item).append("%'")
		      .append(" order by a.Item_Rev")
		      .append(") where rownum=1");
		return strSql.toString();
	}
}
