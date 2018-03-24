package com.szmengran.admin.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class T_power_menu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer bpid;
	private Integer mpid;
	private String name;
	private String icon;
	private String route;
	private Integer displayno;
	private Timestamp createstamp;
	private Timestamp updatestamp;
	private String validstatus;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBpid() {
		return bpid;
	}
	public void setBpid(Integer bpid) {
		this.bpid = bpid;
	}
	public Integer getMpid() {
		return mpid;
	}
	public void setMpid(Integer mpid) {
		this.mpid = mpid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public Integer getDisplayno() {
		return displayno;
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
	public String getValidstatus() {
		return validstatus;
	}
	public void setValidstatus(String validstatus) {
		this.validstatus = validstatus;
	}
	
}
