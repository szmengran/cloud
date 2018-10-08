package com.suntak.cloud.test.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.test.entity.T_oa_test_use_record;
import com.suntak.cloud.test.service.UseRecordService;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.test.service.impl
 * @Description: 使用记录服务
 * @date 2018年6月12日 下午5:15:02
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service("useRecordService")
public class UseRecordServiceImpl implements UseRecordService{

	@Autowired
	AbstractDao abstractDao;
	
	@Override
	public void insert(T_oa_test_use_record t_oa_test_use_record) throws Exception {
		abstractDao.insert(t_oa_test_use_record);
	}

	@Override
	public void updateBackTime(String test_stand_code) throws Exception {
		Object[] params = new Object[2];
		params[0] = new Timestamp(System.currentTimeMillis());
		params[1] = test_stand_code;
		abstractDao.executeSql("update t_oa_test_use_record set back_time = ? where back_time is null and test_stand_code = ?", params);
	}

	@Override
	public T_oa_test_use_record findLastRecordByTestStandCode(String companycode, String test_stand_code) throws Exception {
		Object[] params = new Object[2];
		params[0] = test_stand_code;
		params[1] = companycode;
		StringBuffer strSql = new StringBuffer();
		strSql.append("select * from(select a.*,b.empname,b.companycode from t_oa_test_use_record a, tb_v_rpt_emp_info b")
		.append(" where a.empcode = b.empcode")
		.append(" and a.test_stand_code = ? and b.COMPANYCODE = ?")
		.append(" order by use_time desc ) where rownum = 1");
		List<T_oa_test_use_record> list = abstractDao.findBySql(T_oa_test_use_record.class, strSql.toString(), params);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
