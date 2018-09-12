package com.suntak.cloud.recruitment.service;

import java.util.List;

import com.suntak.cloud.recruitment.entity.T_hr_task;
import com.suntak.cloud.recruitment.entity.ext.T_hr_task_ext;

/**
 * @Package com.suntak.cloud.recruitment.service
 * @Description: 面试任务服务
 * @date 2018年7月19日 上午10:02:26
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface TaskService {
	
	/**
	 * 添加面试任务
	 * @param t_hr_task
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public void insert(T_hr_task t_hr_task) throws Exception;
	
	/**
	 * 处理任务
	 * @param t_hr_task
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public void handlerTask(T_hr_task t_hr_task) throws Exception;
	
	/**
	 * 查找任务
	 * @param roles
	 * @param userid
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public List<T_hr_task_ext> find(String[] roles, String userid) throws Exception;
}
