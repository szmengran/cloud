package com.suntak.cloud.recruitment.entity.ext;

import com.suntak.cloud.recruitment.entity.T_hr_interview;

/**
 * @Package com.suntak.cloud.recruitment.entity.ext
 * @Description: 面试扩展表
 * @date 2018年8月22日 上午9:41:31
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_hr_interview_ext extends T_hr_interview{
	private Integer taskid;
	private String assignrole;
	private String assign;
	public Integer getTaskid() {
		return taskid;
	}
	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}
	public String getAssignrole() {
		return assignrole;
	}
	public void setAssignrole(String assignrole) {
		this.assignrole = assignrole;
	}
	public String getAssign() {
		return assign;
	}
	public void setAssign(String assign) {
		this.assign = assign;
	}
	
}
