package com.suntak.push.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.szmengran.mybatis.utils.Table;

@Table(name = "t_push_robot")
public class TPushRobot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6442891940108381826L;
	private String type;
	private String name;
	private String robotid;
	private String url;
	private String picurl;
	private Integer range_start;
	private Integer range_end;
	private Timestamp createstamp;
	private Integer validstatus;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRobotid() {
		return robotid;
	}
	public void setRobotid(String robotid) {
		this.robotid = robotid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public Integer getRange_start() {
		return range_start;
	}
	public void setRange_start(Integer range_start) {
		this.range_start = range_start;
	}
	public Integer getRange_end() {
		return range_end;
	}
	public void setRange_end(Integer range_end) {
		this.range_end = range_end;
	}
	public Timestamp getCreatestamp() {
		return createstamp;
	}
	public void setCreatestamp(Timestamp createstamp) {
		this.createstamp = createstamp;
	}
	public Integer getValidstatus() {
		return validstatus;
	}
	public void setValidstatus(Integer validstatus) {
		this.validstatus = validstatus;
	}
	
}
