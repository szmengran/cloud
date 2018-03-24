package com.szmengran.admin.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class T_power_user_login_info implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userid;
	private String lastloginip;
	private Timestamp lastlogintime;

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getLastloginip() {
		return lastloginip;
	}
	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}
	public Timestamp getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(Timestamp lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
}
