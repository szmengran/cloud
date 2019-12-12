package com.suntak.cloud.erp.entity;

public class EbsResponse {
	
	private Integer retcode;
	private String errbuf;
	private String x_ebs_number;
	public Integer getRetcode() {
		return retcode;
	}
	public void setRetcode(Integer retcode) {
		this.retcode = retcode;
	}
	public String getErrbuf() {
		return errbuf;
	}
	public void setErrbuf(String errbuf) {
		this.errbuf = errbuf;
	}
	public String getX_ebs_number() {
		return x_ebs_number;
	}
	public void setX_ebs_number(String x_ebs_number) {
		this.x_ebs_number = x_ebs_number;
	}
	
}
