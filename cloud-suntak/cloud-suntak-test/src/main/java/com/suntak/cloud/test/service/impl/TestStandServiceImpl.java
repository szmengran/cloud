package com.suntak.cloud.test.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.test.entity.T_oa_test_stand;
import com.suntak.cloud.test.entity.T_oa_test_use_record;
import com.suntak.cloud.test.entity.ext.T_oa_test_stand_ext;
import com.suntak.cloud.test.entity.ext.TestStandStatus;
import com.suntak.cloud.test.service.TestStandService;
import com.suntak.admin.user.exception.BusinessException;
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
	public List<T_oa_test_stand_ext> findByCompanyCode(String companyCode) throws Exception {
		StringBuffer strSql = new StringBuffer();
		strSql.append("SELECT a.test_stand_code,a.warehouse_code,a.num,")
		.append(" decode(a.validstatus, 0, '作废', 1, '可用', 2, '被领用') status, a.createstamp, a.updatestamp,")
		.append(" a.companycode FROM T_OA_TEST_STAND a where")
		.append(" a.companycode = ?")
		.append(" order by a.validstatus desc,a.warehouse_code,a.test_stand_code");
		Object[] params = new Object[1];
		params[0] = companyCode;
		return abstractDao.findBySql(T_oa_test_stand_ext.class, strSql.toString(), params);
	}
	
	@Override
	public void inuse(String empcode, String test_stand_code, Integer num) throws Exception {
		DBManager dbManager = null;
		try {
			dbManager = new DBManager(abstractDao.getWriteDataSource());
			dbManager.openConnection();
			dbManager.beginTransaction();
			Timestamp stamp = new Timestamp(System.currentTimeMillis());
			Object params[] = new Object[4];
			params[0] = TestStandStatus.INUSE.status;
			params[1] = stamp;
			params[2] = test_stand_code;
			params[3] = num;
			int number = abstractDao.executeSql(dbManager, "update t_oa_test_stand set validstatus=?,updatestamp=? where test_stand_code=? and num=? and validstatus='1'", params);
			//型号不存在，则新增一条
			if (number == 0) {
				throw new BusinessException(6002);
			}
			T_oa_test_use_record t_oa_test_use_record = new T_oa_test_use_record();
			t_oa_test_use_record.setEmpcode(empcode);
			t_oa_test_use_record.setNum(num);
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
	public void giveback(String test_stand_code, String warehouse_code, Integer num) throws Exception {
		DBManager dbManager = null;
		try {
			dbManager = new DBManager(abstractDao.getWriteDataSource());
			dbManager.openConnection();
			dbManager.beginTransaction();
			Timestamp stamp = new Timestamp(System.currentTimeMillis());
			Object params[] = new Object[5];
			params[0] = TestStandStatus.VALID.status;
			params[1] = warehouse_code;
			params[2] = stamp;
			params[3] = test_stand_code;
			params[4] = num;
			if (StringUtils.isBlank(warehouse_code)) {
				throw new BusinessException(6001);
			}
			//更新状态和库位编号
			int number = abstractDao.executeSql(dbManager, "update t_oa_test_stand set validstatus=?,warehouse_code=?,updatestamp=? where test_stand_code=? and num=?", params);
			//型号不存在，则新增一条
			if (number == 0) {
				throw new BusinessException(6002);
			}
			//更新归还时间
			abstractDao.executeSql(dbManager, "update t_oa_test_use_record set back_time=? where test_stand_code=?", new Object[] {stamp, test_stand_code});
			dbManager.commitTransaction();
		} catch (Exception e) {
			dbManager.rollbackTransaction();
			if (e.getMessage().contains("CUX_SOA.FK_T_OA_TES_WAREHOUSE_T_OA_TES")) {
				throw new BusinessException(6003);
			}
			throw e;
		} finally {
			dbManager.close();
		}
		
	}

	@Override
	public void invalid(String test_stand_code, Integer num) throws Exception {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Object[] params = new Object[4];
		params[0] = TestStandStatus.INVAILD.status;
		params[1] = stamp;
		params[2] = test_stand_code;
		params[3] = num;
		abstractDao.executeSql("update t_oa_test_stand set validstatus=?, updatestamp=? where test_stand_code=? and num=?", params);
	}

	@Override
	public void insert(T_oa_test_stand t_oa_test_stand) throws Exception {
		DBManager dbManager = null;
		try {
			dbManager = new DBManager(abstractDao.getWriteDataSource());
			dbManager.openConnection();
			dbManager.beginTransaction();
			//查询最多的套数，以便进行累加
			List<T_oa_test_stand> list = abstractDao.findBySql(dbManager, T_oa_test_stand.class, "select max(num) num from t_oa_test_stand where test_stand_code=?", new Object[]{t_oa_test_stand.getTest_stand_code()});
			int num = 0;
			if (list != null && list.size() > 0) {
				T_oa_test_stand t_oa_test_stand_temp = list.get(0);
				num = t_oa_test_stand_temp.getNum() == null ? 0 : t_oa_test_stand_temp.getNum();
			}
			Timestamp createstamp = new Timestamp(System.currentTimeMillis());
			t_oa_test_stand.setCreatestamp(createstamp);
			t_oa_test_stand.setUpdatestamp(createstamp);
			t_oa_test_stand.setValidstatus(TestStandStatus.VALID.status);
			t_oa_test_stand.setNum(num+1);
			abstractDao.insert(dbManager, t_oa_test_stand);
			dbManager.commitTransaction();
		} catch (Exception e) {
			dbManager.rollbackTransaction();
			throw e;
		} finally {
			dbManager.close();
		}
	}
	
}
