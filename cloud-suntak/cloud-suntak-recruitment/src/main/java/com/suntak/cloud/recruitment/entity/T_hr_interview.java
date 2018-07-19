package com.suntak.cloud.recruitment.entity;

import java.sql.Timestamp;

/**
 * @Package com.suntak.cloud.interview.entity
 * @Description: 面试结果
 * @date 2018年7月19日 上午9:16:11
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_hr_interview {
	private Integer interviewid  ;
	private String applicantid   ;
	private String username      ;
	private String result        ;
	private String pass          ;
	private Timestamp createstamp;
	private Timestamp updatestamp;
	public Integer getInterviewid() {
		return interviewid;
	}
	public void setInterviewid(Integer interviewid) {
		this.interviewid = interviewid;
	}
	public String getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(String applicantid) {
		this.applicantid = applicantid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Timestamp getCreatestamp() {
		return createstamp;
	}
	public void setCreatestamp(Timestamp createstamp) {
		this.createstamp = createstamp;
	}
	public Timestamp getUpdatestamp() {
		return updatestamp;
	}
	public void setUpdatestamp(Timestamp updatestamp) {
		this.updatestamp = updatestamp;
	}
	
}
