package com.suntak.cloud.recruitment.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_interview;
import com.suntak.cloud.recruitment.entity.ext.T_hr_interview_ext;
import com.suntak.cloud.recruitment.service.InterviewService;
import com.szmengran.common.orm.DBManager;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: TODO
 * @date 2018年7月19日 下午2:26:50
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class InterviewServiceImpl implements InterviewService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public void insert(T_hr_interview t_hr_interview) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interviewAndassignTask(T_hr_interview_ext t_hr_interview_ext) throws Exception {
		DBManager dbManager = null;
		try {
			dbManager = new DBManager(abstractDao.getWriteDataSource());
			dbManager.openConnection();
			dbManager.beginTransaction();
			Object[] params = new Object[3];
			params[0] = t_hr_interview_ext.getAssignrole();
			params[1] = t_hr_interview_ext.getAssign();
			params[2] = t_hr_interview_ext.getTaskid();
			abstractDao.executeSql(dbManager, "update t_hr_task set assignrole=?,assign=? where taskid=?", params);
			T_hr_interview t_hr_interview = new T_hr_interview();
			BeanUtils.copyProperties(t_hr_interview_ext, t_hr_interview);
			abstractDao.insert(dbManager, t_hr_interview);
			dbManager.commitTransaction();
		} catch(Exception e) {
			dbManager.rollbackTransaction();
			throw e;
		} finally {
			dbManager.close();
		}
	}
}
