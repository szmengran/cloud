package com.suntak.cloud.haiwd.entity;

import java.sql.Timestamp;

import com.szmengran.mybatis.utils.Table;

@Table(id = "userid,checkin_time")
public class T_haiwd_checkindata {
	private String userid;
	private String groupname;
	private String checkin_type;
	private String exception_type;
	private Long checkin_time;
	private String location_title;
	private String location_detail;
	private String wifiname;
	private String notes;
	private String wifimac;
	private String status;
	private Timestamp createstamp;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getCheckin_type() {
		return checkin_type;
	}
	public void setCheckin_type(String checkin_type) {
		this.checkin_type = checkin_type;
	}
	public String getException_type() {
		return exception_type;
	}
	public void setException_type(String exception_type) {
		this.exception_type = exception_type;
	}
	public Long getCheckin_time() {
		return checkin_time;
	}
	public void setCheckin_time(Long checkin_time) {
		this.checkin_time = checkin_time;
	}
	public String getLocation_title() {
		return location_title;
	}
	public void setLocation_title(String location_title) {
		this.location_title = location_title;
	}
	public String getLocation_detail() {
		return location_detail;
	}
	public void setLocation_detail(String location_detail) {
		this.location_detail = location_detail;
	}
	public String getWifiname() {
		return wifiname;
	}
	public void setWifiname(String wifiname) {
		this.wifiname = wifiname;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getWifimac() {
		return wifimac;
	}
	public void setWifimac(String wifimac) {
		this.wifimac = wifimac;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreatestamp() {
		return createstamp;
	}
	public void setCreatestamp(Timestamp createstamp) {
		this.createstamp = createstamp;
	}
}
