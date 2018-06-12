package com.suntak.cloud.test.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.test.entity.T_oa_test_stand;
import com.suntak.cloud.test.entity.T_oa_test_use_record;
import com.suntak.cloud.test.entity.ext.TestStandStatus;
import com.suntak.cloud.test.service.TestStandService;
import com.szmengran.common.orm.DBManager;
import com.szmengran.common.orm.DbPrimaryKeyType;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.test.service.impl
 * @Description: 测试架
 * @date 2018年6月11日 下午5:02:44
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service("testStandService")
public class TestStandServiceImpl implements TestStandService{

	@Autowired
	AbstractDao abstractDao;
	
	@Override
	public List<T_oa_test_stand> findByConditions(StringBuffer conditions, Object[] params) throws Exception {
		return abstractDao.findByConditions(T_oa_test_stand.class, conditions, params);
	}

	@Override
	public void inuse(String empcode, String test_stand_code) throws Exception {
		DBManager dbManager = null;
		try {
			dbManager = new DBManager(abstractDao.getWriteDataSource());
			dbManager.beginTransaction();
			Object params[] = new Object[2];
			params[0] = TestStandStatus.INUSE.status;
			params[1] = test_stand_code;
			int num = abstractDao.executeSql(dbManager, "update t_oa_test_stand set validstatus=? where test_stand_code=?", params);
			if (num == 0) {
				
			}
			T_oa_test_use_record t_oa_test_use_record = new T_oa_test_use_record();
			t_oa_test_use_record.setEmpcode(empcode);
			t_oa_test_use_record.setTest_stand_code(test_stand_code);
			t_oa_test_use_record.setUse_time(new Timestamp(System.currentTimeMillis()));
			abstractDao.insert(dbManager, t_oa_test_use_record, DbPrimaryKeyType.SEQ, "seq_t_oa_test_use_record");
			dbManager.commitTransaction();
		} catch (Exception e) {
			dbManager.rollbackTransaction();
			throw e;
		} finally {
			dbManager.close();
		}
		
	}

	@Override
	public void giveback(String test_stand_code) throws Exception {
		
		
	}

	@Override
	public void insert(T_oa_test_stand t_oa_test_stand) throws Exception {
		Timestamp createstamp = new Timestamp(System.currentTimeMillis());
		t_oa_test_stand.setCreatestamp(createstamp);
		t_oa_test_stand.setUpdatestamp(createstamp);
		t_oa_test_stand.setValidstatus(TestStandStatus.VALID.status);
		abstractDao.insert(t_oa_test_stand);
	}
	
}
