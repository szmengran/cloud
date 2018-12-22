package com.suntak.cloud.ehr.entity;

import java.sql.Timestamp;

/**
 * @Package com.suntak.cloud.ehr.entity
 * @Description: 企业微信通讯录人员信息
 * @date Dec 17, 2018 10:04:34 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class ContactExt extends Contact{
	private Integer deptid;
	private Timestamp labordate;
	private Timestamp operate_time;
	private Timestamp mobile_operate_time;
	
	public Integer getDeptid() {
		return deptid;
	}
	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}
	public Timestamp getLabordate() {
		return labordate;
	}
	public void setLabordate(Timestamp labordate) {
		this.labordate = labordate;
	}
	public Timestamp getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(Timestamp operate_time) {
		this.operate_time = operate_time;
	}
	public Timestamp getMobile_operate_time() {
		return mobile_operate_time;
	}
	public void setMobile_operate_time(Timestamp mobile_operate_time) {
		this.mobile_operate_time = mobile_operate_time;
	}
}
