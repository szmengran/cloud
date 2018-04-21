package com.suntak.cloud.questionnaire.entity.other;
/**
 * @Package com.suntak.cloud.questionnaire.entity.other
 * @Description: 问卷调查结果表
 * @date 2018年4月21日 上午11:03:45
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Questionnaire {
	private Integer userid;
	private String empcode;
	private String empname;
	private String deptname;
	private String job_level;
	private String phone;
	private String yearmonth;
	private Integer totalcount;
	private Integer alreadycount;
	private Double avgscore;
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getEmpcode() {
		return empcode;
	}
	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getJob_level() {
		return job_level;
	}
	public void setJob_level(String job_level) {
		this.job_level = job_level;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getYearmonth() {
		return yearmonth;
	}
	public void setYearmonth(String yearmonth) {
		this.yearmonth = yearmonth;
	}
	public Integer getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}
	public Integer getAlreadycount() {
		return alreadycount;
	}
	public void setAlreadycount(Integer alreadycount) {
		this.alreadycount = alreadycount;
	}
	public Double getAvgscore() {
		return avgscore;
	}
	public void setAvgscore(Double avgscore) {
		this.avgscore = avgscore;
	}
}
