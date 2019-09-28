package com.suntak.push.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.szmengran.mybatis.utils.Table;

@Table(name = "cux.cux_soa_mi_push")
public class CuxSoaMiPush extends Push implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mi_status;
	private String rev;
	private Timestamp sch_date;
	public String getMi_status() {
		return mi_status;
	}
	public void setMi_status(String mi_status) {
		this.mi_status = mi_status;
	}
	public String getRev() {
		return rev;
	}
	public void setRev(String rev) {
		this.rev = rev;
	}
	public Timestamp getSch_date() {
		return sch_date;
	}
	public void setSch_date(Timestamp sch_date) {
		this.sch_date = sch_date;
	}
	
}
