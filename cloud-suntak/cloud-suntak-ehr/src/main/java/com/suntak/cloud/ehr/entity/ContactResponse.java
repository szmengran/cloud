package com.suntak.cloud.ehr.entity;
/**
 * @Package com.suntak.cloud.ehr.entity
 * @Description: TODO
 * @date Dec 17, 2018 10:35:52 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class ContactResponse {
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
