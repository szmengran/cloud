package com.suntak.push.entity;

import java.io.Serializable;

public class RobotResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2725292194083484909L;
	private Integer errcode;
	private String errmsg;
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
	
}
