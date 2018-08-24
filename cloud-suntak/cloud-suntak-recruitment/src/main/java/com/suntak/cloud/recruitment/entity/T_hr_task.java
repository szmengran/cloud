package com.suntak.cloud.recruitment.entity;

import java.sql.Timestamp;

/**
 * @Package com.suntak.cloud.recruitment.entity
 * @Description: 任务表
 * @date 2018年8月17日 下午4:42:27
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_hr_task {
	private String taskid;
	private String applicantid;
	private Integer workflowid;
	private String title;
	private String assignrole;
	private String assign;
	private Short status;
	private Timestamp createstamp;
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
	public Integer getWorkflowid() {
		return workflowid;
	}
	public void setWorkflowid(Integer workflowid) {
		this.workflowid = workflowid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Timestamp getCreatestamp() {
		return createstamp;
	}
	public void setCreatestamp(Timestamp createstamp) {
		this.createstamp = createstamp;
	}
}
