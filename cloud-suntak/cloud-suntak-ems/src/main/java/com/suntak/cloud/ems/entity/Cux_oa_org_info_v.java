package com.suntak.cloud.ems.entity;

import com.szmengran.mybatis.utils.Table;

@Table(name = "Cux_oa_org_info_v@to_prod")
public class Cux_oa_org_info_v {

	private Integer org_id;
	private String org_name;
	private String org_code;
	public Integer getOrg_id() {
		return org_id;
	}
	public void setOrg_id(Integer org_id) {
		this.org_id = org_id;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getOrg_code() {
		return org_code;
	}
	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}
	
}
