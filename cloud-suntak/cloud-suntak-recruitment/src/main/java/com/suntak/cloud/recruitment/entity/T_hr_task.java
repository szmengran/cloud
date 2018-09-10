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
	private Integer subflowid;
	private String assignrole;
	private String assign;
	private String remark;
	private String agree;
	private Short status;
	private Timestamp createstamp;
	private Timestamp updatestamp;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAgree() {
		return agree;
	}
	public void setAgree(String agree) {
		this.agree = agree;
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
	public Integer getSubflowid() {
		return subflowid;
	}
	public void setSubflowid(Integer subflowid) {
		this.subflowid = subflowid;
	}
	public Timestamp getUpdatestamp() {
		return updatestamp;
	}
	public void setUpdatestamp(Timestamp updatestamp) {
		this.updatestamp = updatestamp;
	}
}
