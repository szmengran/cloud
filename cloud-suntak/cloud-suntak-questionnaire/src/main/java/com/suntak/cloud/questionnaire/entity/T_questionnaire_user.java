package com.suntak.cloud.questionnaire.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Package com.suntak.cloud.questionnaire.entity
 * @Description: 用户
 * @date 2018年4月18日 下午3:27:49
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_questionnaire_user implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userid;
	private String empcode;
	private String empname;
	private String password;
	private Integer displayno;
	private String validstatus;
	private Timestamp createstamp;
	private Timestamp updatestamp;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getDisplayno() {
		return displayno;
	}
	public String getValidstatus() {
		return validstatus;
	}
	public void setValidstatus(String validstatus) {
		this.validstatus = validstatus;
	}
	public void setDisplayno(Integer displayno) {
		this.displayno = displayno;
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
