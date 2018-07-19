package com.suntak.cloud.recruitment.entity;

import java.sql.Timestamp;

/**
 * @Package com.suntak.cloud.interview.entity
 * @Description: 应聘者评级
 * @date 2018年7月19日 上午9:06:17
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_hr_applicant_rating {
	private String applicantid   ;
	private String rank          ;
	private Float salary         ;
	private String createby      ;
	private Timestamp createstamp;
	private Timestamp updatestamp;
	public String getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(String applicantid) {
		this.applicantid = applicantid;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
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
