package com.suntak.cloud.activity.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.suntak.cloud.activity.entity.T_activity_apply;
import com.suntak.cloud.activity.entity.T_activity_group;
import com.suntak.cloud.activity.entity.ext.T_activity_apply_ext;
import com.suntak.cloud.activity.service.T_activity_applyService;
import com.szmengran.common.orm.DBManager;
import com.szmengran.common.orm.DbPrimaryKeyType;
import com.szmengran.common.orm.service.AbstractService;

@Service
public class T_activity_applyServiceImpl extends AbstractService implements T_activity_applyService{

	@Override
	public void apply(String username, Integer service_id, List<T_activity_apply> t_activity_applys) throws Exception {
		DBManager dbManager = new DBManager(super.getWriteDataSource());
		try {
			dbManager.openConnection();
			dbManager.beginTransaction();
			Timestamp createstamp = new Timestamp(System.currentTimeMillis());
			if (t_activity_applys != null && t_activity_applys.size() > 1) {
				String strSql = "select seq_t_activity_group.nextval as group_id from dual";
				List<Object> list = super.findBySql(new T_activity_group(), strSql, null);
				if (list != null && list.size() > 0) {
					T_activity_group t_group = (T_activity_group)list.get(0);
					super.save(dbManager, t_group);
					List<T_activity_apply> t_activity_applyList = new ArrayList<T_activity_apply>();
					for (T_activity_apply t_activity_apply:t_activity_applys) {
						t_activity_apply.setIsnight(t_activity_apply.getIsnight().equalsIgnoreCase("true")?"白班":"夜班");
						t_activity_apply.setGroup_id(t_group.getGroup_id());
						t_activity_apply.setService_id(service_id);
						t_activity_apply.setYear(Calendar.getInstance().get(Calendar.YEAR));
						t_activity_apply.setUsername(username);
						t_activity_apply.setCreatestamp(createstamp);
						t_activity_apply.setUpdatestamp(createstamp);
						t_activity_applyList.add(t_activity_apply);
					}
					super.addBatch(dbManager, t_activity_applyList, DbPrimaryKeyType.SEQ, "seq_t_activity_apply");
					dbManager.commitBatch();
				}
			} else if(t_activity_applys != null && t_activity_applys.size() == 1) {
				for (T_activity_apply t_activity_apply:t_activity_applys) {
					t_activity_apply.setIsnight(t_activity_apply.getIsnight().equals("true")?"白班":"夜班");
					t_activity_apply.setService_id(service_id);
					t_activity_apply.setYear(Calendar.getInstance().get(Calendar.YEAR));
					t_activity_apply.setUsername(username);
					t_activity_apply.setCreatestamp(createstamp);
					t_activity_apply.setUpdatestamp(createstamp);
					super.save(dbManager, t_activity_apply, DbPrimaryKeyType.SEQ, "seq_t_activity_apply");
				}
			}
			dbManager.commitTransaction();
		} catch (Exception e) {
			dbManager.rollbackTransaction();
			throw e;
		} finally {
			dbManager.close();
		}
	}

	@Override
	public Boolean signin(String username, Integer apply_id) throws Exception {
		String strSql = "update t_activity_apply set validstatus='1',updateby=? where apply_id=?";
		Object[] params = new Object[2];
		params[0] = username;
		params[1] = apply_id;
		int count = super.executeSql(strSql, params);
		return (count == 1);
	}

	@Override
	public List<T_activity_apply_ext> findByConditions(Map<String, Object> params) throws Exception {
		StringBuffer strSql = new StringBuffer();
		strSql.append("select a.*,b.DEPTNAME,b.KENAME,b.COMPANYNAME,b.C_MOBILE_TEL phone,b.GENDER sex")
		.append(" from T_ACTIVITY_APPLY A left join TB_V_RPT_EMP_INFO B on a.empcode=b.empcode where 1=1 ");
		Object[] values = null;
		if(params != null){
			values = new Object[params.size()];
			Set<String> set = params.keySet();
			int index = 0;
			for(String key:set){
				strSql.append(" AND ").append(key).append(" = ?");
				values[index++] = params.get(key);
			}
		}
		strSql.append(" order by a.validstatus desc, b.companycode,b.deptname,b.kename,a.empcode");
		return super.findBySql(new T_activity_apply_ext(), strSql.toString(), values);
	}
	

}
