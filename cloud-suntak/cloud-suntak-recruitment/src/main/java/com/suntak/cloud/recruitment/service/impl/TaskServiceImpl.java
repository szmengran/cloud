package com.suntak.cloud.recruitment.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suntak.cloud.recruitment.entity.T_hr_task;
import com.suntak.cloud.recruitment.entity.T_hr_workflow_sub;
import com.suntak.cloud.recruitment.entity.ext.T_hr_task_ext;
import com.suntak.cloud.recruitment.mapper.TaskMapper;
import com.suntak.cloud.recruitment.mapper.WorkflowSubMapper;
import com.suntak.cloud.recruitment.service.TaskService;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: 任务服务
 * @date 2018年8月22日 上午9:14:22
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class TaskServiceImpl implements TaskService {

    private final static ExecutorService executor = new ThreadPoolExecutor(20, 200, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
	
	@Autowired
	private TaskMapper<T_hr_task> taskMapper;
	
	@Autowired
	private WorkflowSubMapper<T_hr_workflow_sub> workflowSubMapper;
	
	@Override
	public void insert(T_hr_task t_hr_task) throws Exception {
		t_hr_task.setStatus((short)1);
		t_hr_task.setCreatestamp(new Timestamp(System.currentTimeMillis()));
		t_hr_task.setUpdatestamp(new Timestamp(System.currentTimeMillis()));
		taskMapper.insert(t_hr_task);
	}
	
	@Transactional
	@Override
	public T_hr_workflow_sub handlerTask(T_hr_task t_hr_task) throws Exception {
		try{
			Future<T_hr_workflow_sub> future = executor.submit(() -> {
				List<T_hr_workflow_sub> list = workflowSubMapper.findWorkflowSub(t_hr_task.getSubflowid(), t_hr_task.getAgree());
				if (list != null && list.size() > 0) {
					return list.get(0);
				}
				return null;
			});

			taskMapper.updateTask(t_hr_task);
			
			T_hr_workflow_sub t_hr_workflow_sub = future.get();
			if (t_hr_workflow_sub != null) {
			    T_hr_task task = new T_hr_task();
			    task.setAssignrole(t_hr_workflow_sub.getRole());
			    task.setSubflowid(t_hr_workflow_sub.getSubflowid());
			    task.setAssign(t_hr_task.getAssign());
			    task.setApplicantid(t_hr_task.getApplicantid());
			    task.setStatus((short)1);
			    task.setCreatestamp(new Timestamp(System.currentTimeMillis()));
			    task.setUpdatestamp(new Timestamp(System.currentTimeMillis()));
				taskMapper.insert(task);
			}
			
			return t_hr_workflow_sub;
		} catch (DuplicateKeyException e) {
		  throw new Exception("该任务已经处理，请勿重复处理！");  
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<T_hr_task_ext> find(String[] roles, String userid) throws Exception {
		StringBuilder strRoles = new StringBuilder();
		if (roles != null) {
    		for (String role:roles) {
    			strRoles.append(",'").append(role).append("'");
    		}
		} else {
		    strRoles.append(",''");
		}
		return taskMapper.findTask(strRoles.substring(1), userid);
	}

}
