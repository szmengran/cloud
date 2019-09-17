package com.suntak.cloud.haiwd.entity;

public class CheckindataRequest {
	private Integer opencheckindatatype;
	private Long starttime;
	private Long endtime;
	private String[] useridlist;
	public Integer getOpencheckindatatype() {
		return opencheckindatatype;
	}
	public void setOpencheckindatatype(Integer opencheckindatatype) {
		this.opencheckindatatype = opencheckindatatype;
	}
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}
	public String[] getUseridlist() {
		return useridlist;
	}
	public void setUseridlist(String[] useridlist) {
		this.useridlist = useridlist;
	}
	
}
