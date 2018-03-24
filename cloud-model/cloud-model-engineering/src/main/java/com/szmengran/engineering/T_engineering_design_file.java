package com.szmengran.engineering;

import java.io.Serializable;
import java.sql.Timestamp;

public class T_engineering_design_file implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 267462384623864L;
	private String fileid;
	private String engineering_id;
	private String validstatus;
	private String remark;
	private Timestamp createstamp;
	private Timestamp updatestamp;
	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	public String getEngineering_id() {
		return engineering_id;
	}
	public void setEngineering_id(String engineering_id) {
		this.engineering_id = engineering_id;
	}
	public String getValidstatus() {
		return validstatus;
	}
	public void setValidstatus(String validstatus) {
		this.validstatus = validstatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
