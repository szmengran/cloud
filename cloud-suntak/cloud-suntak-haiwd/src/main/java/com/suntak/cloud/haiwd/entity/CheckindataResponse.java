package com.suntak.cloud.haiwd.entity;

import java.io.Serializable;

public class CheckindataResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 534673432423423654L;
	private Integer errcode;
	private String errmsg;
	private T_haiwd_checkindata[] checkindata;
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public T_haiwd_checkindata[] getCheckindata() {
		return checkindata;
	}
	public void setCheckindata(T_haiwd_checkindata[] checkindata) {
		this.checkindata = checkindata;
	}
	
}
