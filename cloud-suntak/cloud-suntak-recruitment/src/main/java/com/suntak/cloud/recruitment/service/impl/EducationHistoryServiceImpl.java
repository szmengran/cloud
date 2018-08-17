package com.suntak.cloud.recruitment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_educationhistory;
import com.suntak.cloud.recruitment.service.EducationHistoryService;
import com.szmengran.common.orm.DBManager;
import com.szmengran.common.orm.DbPrimaryKeyType;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: 学习经历
 * @date 2018年7月19日 下午2:24:51
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class EducationHistoryServiceImpl implements EducationHistoryService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public void saveOrUpdateList(String applicantid, List<T_hr_educationhistory> t_hr_educationhistory) throws Exception {
		DBManager dbManager = new DBManager(abstractDao.getWriteDataSource());
		dbManager.beginTransaction();
		abstractDao.executeSql(dbManager, "delete from t_hr_educationhistory where applicantid=?", new Object[] {applicantid});
		abstractDao.addBatch(dbManager, t_hr_educationhistory, DbPrimaryKeyType.SEQ, "seq_t_hr_educationhistory");
		dbManager.commitBatch();
		dbManager.commitTransaction();
	}
	
	@Override
	public void saveOrUpdate(T_hr_educationhistory t_hr_educationhistory) throws Exception {
		if (t_hr_educationhistory.getEducationhistoryid() == null) {
			abstractDao.insert(t_hr_educationhistory, DbPrimaryKeyType.SEQ, "seq_t_hr_educationhistory");
		} else {
			abstractDao.update(t_hr_educationhistory);
		}
	}
	
	@Override
	public void delete(Integer educationhistoryid) throws Exception {
		T_hr_educationhistory t_hr_educationhistory = new T_hr_educationhistory();
		t_hr_educationhistory.setEducationhistoryid(educationhistoryid);
		abstractDao.delete(t_hr_educationhistory);
	}

	@Override
	public List<T_hr_educationhistory> findByApplicantid(String applicantid) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("applicantid", applicantid);
		return abstractDao.findByConditions(T_hr_educationhistory.class, params);
	}
}
