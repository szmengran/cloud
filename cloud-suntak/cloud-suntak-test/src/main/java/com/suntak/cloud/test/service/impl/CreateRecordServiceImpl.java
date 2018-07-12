package com.suntak.cloud.test.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.test.entity.Cux_soa_ft_data_v;
import com.suntak.cloud.test.entity.T_oa_test_create_record;
import com.suntak.cloud.test.service.CreateRecordService;
import com.szmengran.common.orm.DBManager;
import com.szmengran.common.orm.DbPrimaryKeyType;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.test.service.impl
 * @Description: 资料制作操作
 * @date 2018年6月25日 下午2:17:02
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class CreateRecordServiceImpl implements CreateRecordService{

	@Autowired
	AbstractDao abstractDao;
	
	@Override
	public void insert(T_oa_test_create_record t_oa_test_create_record) throws Exception {
		t_oa_test_create_record.setCreate_time(new Timestamp(System.currentTimeMillis()));
		abstractDao.insert(t_oa_test_create_record, DbPrimaryKeyType.SEQ, "seq_t_oa_test_create_record");
	}

	@Override
	public void delete(Integer wip_entity_id, String empcode) throws Exception {
		abstractDao.executeSql("delete from t_oa_test_create_record where wip_entity_id = ? and empcode = ?", new Object[] {wip_entity_id, empcode});
	}
	
	@Override
	public void finish(Integer wip_entity_id) throws Exception {
		DBManager dbManager = null;
		try {
			dbManager = new DBManager(abstractDao.getWriteDataSource());
			dbManager.openConnection();
			dbManager.beginTransaction();
			Object[] args = new Object[1];
			args[0] = wip_entity_id;
			String strSql = "select a.wip_entity_id,a.ORGANIZATION_ID,a.SEGMENT1,a.Item_Rev,a.DEPT,a.CHECK_TYPE,a.CHECK_STATUS,a.INV_DATE from apps.Cux_soa_ft_data_v a where wip_entity_id = ?";
			List<Cux_soa_ft_data_v> list = abstractDao.findBySql(dbManager, Cux_soa_ft_data_v.class, strSql, args);
			if (list != null && list.size() > 0) {
				Cux_soa_ft_data_v cux_soa_ft_data_v = list.get(0);
				Object[] params = new Object[2];
				params[0] = new Timestamp(System.currentTimeMillis());
				params[1] = wip_entity_id;
				abstractDao.executeSql(dbManager, "update t_oa_test_create_record set finish_time=? where wip_entity_id=?", params);
				if (!"待钻孔".equals(cux_soa_ft_data_v.getCheck_status())) {
					String status = this.getNextStatus(cux_soa_ft_data_v.getCheck_type(), cux_soa_ft_data_v.getCheck_status());
					this.updateStatus(dbManager, cux_soa_ft_data_v.getOrganization_id(), cux_soa_ft_data_v.getSegment1(), cux_soa_ft_data_v.getItem_rev(), status);
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
	
	private String getNextStatus(String check_type, String check_status) {
		if ("通用".equals(check_type)) {
			if ("待做".equals(check_status) || "待做资料".equals(check_status) || StringUtils.isBlank(check_status)) {
				return "待钻孔";
			} else if ("待钻孔".equals(check_status)) {
				return "待钻孔";
			} else if ("待装".equals(check_status)) {
				return "OK";
			}
		} else if ("飞针".equals(check_type)) {
			if ("待做".equals(check_status) || "待做资料".equals(check_status) || StringUtils.isBlank(check_status)) {
				return "OK";
			}
		}
		return check_status;
	}
	
	private Boolean updateStatus(DBManager dbManager, Integer organization_id, String segment1, String item_rev, String status) throws Exception {
		String strSql = "{call apps.cux_soa_process_pubpkg.upd_ft_status(?,?,?,?,?,?)}";
		Object[] params = new Object[4];
		params[0] = organization_id;
		params[1] = segment1;
		params[2] = item_rev;
		params[3] = status;
		int[] output = new int[2];
		output[0] = java.sql.Types.VARCHAR;
		output[1] = java.sql.Types.VARCHAR;
		Object[] obj = abstractDao.prepareCall(dbManager, strSql, params, output);
		if ("S".equalsIgnoreCase((String)obj[0])) {
			return true;
		} else {
			throw new Exception((String)obj[1]);
		}
	}
	
	@Override
	public Boolean updateStatus(Integer organization_id, String segment1, String item_rev) throws Exception {
		String strSql = "{call apps.cux_soa_process_pubpkg.upd_ft_status(?,?,?,?,?)}";
		Object[] params = new Object[3];
		params[0] = organization_id;
		params[1] = segment1;
		params[2] = item_rev;
		int[] output = new int[2];
		output[0] = java.sql.Types.VARCHAR;
		output[1] = java.sql.Types.VARCHAR;
		Object[] obj = abstractDao.prepareCall(strSql, params, output);
		if ("S".equalsIgnoreCase((String)obj[0])) {
			return true;
		} else {
			throw new Exception((String)obj[1]);
		}
	}

}
