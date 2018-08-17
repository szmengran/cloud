package com.suntak.cloud.recruitment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_workhistory;
import com.suntak.cloud.recruitment.service.WorkHistoryService;
import com.szmengran.common.orm.DbPrimaryKeyType;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: 工作经历服务
 * @date 2018年7月19日 下午2:36:41
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class WorkHistoryServiceImpl implements WorkHistoryService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public void saveOrUpdate(T_hr_workhistory t_hr_workhistory) throws Exception {
		if (t_hr_workhistory.getWorkhistoryid() == null) {
			abstractDao.insert(t_hr_workhistory, DbPrimaryKeyType.SEQ, "seq_t_hr_workhistory");
		} else {
			abstractDao.update(t_hr_workhistory);
		}
	}

	@Override
	public List<T_hr_workhistory> findByApplicantid(String applicantid) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("applicantid", applicantid);
		return abstractDao.findByConditions(T_hr_workhistory.class, params);
	}
	

	@Override
	public void delete(Integer workhistoryid) throws Exception {
		T_hr_workhistory t_hr_workhistory = new T_hr_workhistory();
		t_hr_workhistory.setWorkhistoryid(workhistoryid);
		abstractDao.delete(t_hr_workhistory);
	}
}
