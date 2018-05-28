package com.suntak.cloud.activity.entity.ext;

import com.suntak.cloud.activity.entity.T_activity_apply;

/**
 * @Package com.suntak.cloud.activity.entity.ext
 * @Description: 活动报名扩展表
 * @date 2018年5月24日 下午3:14:58
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_activity_apply_ext extends T_activity_apply{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1322222222432432L;
	private String companycode;
	private String deptname;
	private String kename;
	private String sex;
	private String phone;
	
	public String getCompanycode() {
		return companycode;
	}
	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getKename() {
		return kename;
	}
	public void setKename(String kename) {
		this.kename = kename;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
