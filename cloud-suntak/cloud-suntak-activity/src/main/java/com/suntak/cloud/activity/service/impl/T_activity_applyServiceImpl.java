package com.suntak.cloud.activity.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.suntak.cloud.activity.entity.T_activity_apply;
import com.suntak.cloud.activity.entity.T_activity_group;
import com.suntak.cloud.activity.service.T_activity_applyService;
import com.szmengran.common.orm.DBManager;
import com.szmengran.common.orm.DbPrimaryKeyType;
import com.szmengran.common.orm.service.AbstractService;

@Service
public class T_activity_applyServiceImpl extends AbstractService implements T_activity_applyService{

	@Override
	public void apply(Integer service_id, List<T_activity_apply> t_activity_applys) throws Exception {
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
						t_activity_apply.setCreatestamp(createstamp);
						t_activity_apply.setUpdatestamp(createstamp);
						t_activity_apply.setValidstatus("1");
						t_activity_applyList.add(t_activity_apply);
					}
					super.addBatch(dbManager, t_activity_applyList, DbPrimaryKeyType.SEQ, "seq_t_activity_apply");
					dbManager.commitBatch();
				}
			} else if(t_activity_applys != null && t_activity_applys.size() == 1) {
				for (T_activity_apply t_activity_apply:t_activity_applys) {
					t_activity_apply.setIsnight(t_activity_apply.getIsnight().equals("true")?"白班":"夜班");
					t_activity_apply.setService_id(service_id);
					t_activity_apply.setCreatestamp(createstamp);
					t_activity_apply.setUpdatestamp(createstamp);
					t_activity_apply.setValidstatus("1");
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

}
