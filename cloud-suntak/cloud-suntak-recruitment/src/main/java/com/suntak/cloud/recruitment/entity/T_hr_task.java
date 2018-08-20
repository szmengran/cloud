package com.suntak.cloud.recruitment.entity;
/**
 * @Package com.suntak.cloud.recruitment.entity
 * @Description: 任务表
 * @date 2018年8月17日 下午4:42:27
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_hr_task {
	private String taskid;
	private String applicantid;
	private String assignrole;
	private String assign;
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(String applicantid) {
		this.applicantid = applicantid;
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
