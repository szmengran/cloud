package com.suntak.cloud.haiwd.entity;

import java.sql.Timestamp;

import com.szmengran.mybatis.utils.Table;

@Table(id = "userid")
public class T_haiwd_user {
	private String userid;
	private String name;
	private String type;
	private String userid2;
	private Timestamp createstamp;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserid2() {
		return userid2;
	}
	public void setUserid2(String userid2) {
		this.userid2 = userid2;
	}
	public Timestamp getCreatestamp() {
		return createstamp;
	}
	public void setCreatestamp(Timestamp createstamp) {
		this.createstamp = createstamp;
	}
}
