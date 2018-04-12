package com.suntak.ehr.entity;

import java.io.Serializable;

/**
 * @Package com.suntak.cloud.ehr.entity
 * @Description: TODO
 * @date 2018年4月11日 下午2:22:17
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class EhrUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String empcode;
	private String empname;
	private String phone;
	public EhrUser() {
		
	}
	public EhrUser(String empcode, String empname, String phone) {
		super();
		this.empcode = empcode;
		this.empname = empname;
		this.phone = phone;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
