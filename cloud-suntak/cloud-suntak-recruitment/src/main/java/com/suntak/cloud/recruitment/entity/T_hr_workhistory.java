package com.suntak.cloud.recruitment.entity;

import java.sql.Timestamp;

import com.szmengran.mybatis.utils.GeneratedValue;
import com.szmengran.mybatis.utils.GenerationType;
import com.szmengran.mybatis.utils.Table;

/**
 * @Package com.suntak.cloud.interview.entity
 * @Description: 工作经历
 * @date 2018年7月19日 上午9:26:06
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "workhistoryid")
public class T_hr_workhistory {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_t_hr_workhistory")
	private Integer workhistoryid   ;
	private String applicantid     ;
	private Timestamp starttime     ;
	private Timestamp endtime       ;
	private String company          ;
	private String department       ;
	private String position         ;
	private Float salary            ;
	private String resignationreason;
	public Integer getWorkhistoryid() {
		return workhistoryid;
	}
	public void setWorkhistoryid(Integer workhistoryid) {
		this.workhistoryid = workhistoryid;
	}
	public String getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(String applicantid) {
		this.applicantid = applicantid;
	}
	public Timestamp getStarttime() {
		return starttime;
	}
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	public Timestamp getEndtime() {
		return endtime;
	}
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	public String getResignationreason() {
		return resignationreason;
	}
	public void setResignationreason(String resignationreason) {
		this.resignationreason = resignationreason;
	}
	
}
