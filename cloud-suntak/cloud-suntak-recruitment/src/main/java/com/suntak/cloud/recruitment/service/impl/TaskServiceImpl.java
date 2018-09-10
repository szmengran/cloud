package com.suntak.cloud.recruitment.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_task;
import com.suntak.cloud.recruitment.entity.ext.T_hr_task_ext;
import com.suntak.cloud.recruitment.service.TaskService;
import com.szmengran.common.orm.DbPrimaryKeyType;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: 任务服务
 * @date 2018年8月22日 上午9:14:22
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public void insert(T_hr_task t_hr_task) throws Exception {
		t_hr_task.setStatus((short)1);
		t_hr_task.setCreatestamp(new Timestamp(System.currentTimeMillis()));
		t_hr_task.setUpdatestamp(new Timestamp(System.currentTimeMillis()));
		abstractDao.insert(t_hr_task, DbPrimaryKeyType.SEQ, "SEQ_T_HR_TASK");
	}

	@Override
	public List<T_hr_task_ext> find(String[] roles, String userid) throws Exception {
		StringBuilder strRoles = new StringBuilder();
		for (String role:roles) {
			strRoles.append(",'").append(role).append("'");
		}
		Object[] params = new Object[1];
		params[0] = userid;
		StringBuilder strSql = new StringBuilder();
		strSql.append("SELECT a.*,c.name, b.subflowname, b.nextflowid, b.url FROM t_hr_task a ,t_hr_workflow_sub b, t_hr_workflow_main c")
		.append(" where a.subflowid = b.subflowid")
		.append(" and b.workflowid = c.workflowid")
		.append(" and a.status=1")
		.append(" and ((assignrole in (")
		.append(strRoles.substring(1))
		.append(" ) and assign is null) or assign =?) ");
		return abstractDao.findBySql(T_hr_task_ext.class, strSql.toString(), params);
	}

}
