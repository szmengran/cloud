package com.suntak.cloud.ehr.entity;

import java.io.Serializable;

import com.szmengran.mybatis.utils.Table;

@Table(name = "tp_v_code_resource_cn")
public class CodeResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4726355867249681648L;
	private String c_code;
	private String c_value;
	private String c_type_code;
	public String getC_code() {
		return c_code;
	}
	public void setC_code(String c_code) {
		this.c_code = c_code;
	}
	public String getC_value() {
		return c_value;
	}
	public void setC_value(String c_value) {
		this.c_value = c_value;
	}
	public String getC_type_code() {
		return c_type_code;
	}
	public void setC_type_code(String c_type_code) {
		this.c_type_code = c_type_code;
	}
	
}
