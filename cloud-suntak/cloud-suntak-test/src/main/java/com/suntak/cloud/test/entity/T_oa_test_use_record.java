package com.suntak.cloud.test.entity;

import java.sql.Timestamp;

/**
 * @Package com.suntak.cloud.test.entity
 * @Description: 使用记录
 * @date 2018年6月12日 下午4:44:21
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_oa_test_use_record {
	private Integer use_record_id;
	private String test_stand_code;
	private String empcode;
	private Timestamp use_time;
	private Timestamp back_time;
	public Integer getUse_record_id() {
		return use_record_id;
	}
	public void setUse_record_id(Integer use_record_id) {
		this.use_record_id = use_record_id;
	}
	public String getTest_stand_code() {
		return test_stand_code;
	}
	public void setTest_stand_code(String test_stand_code) {
		this.test_stand_code = test_stand_code;
	}
	public String getEmpcode() {
		return empcode;
	}
	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}
	public Timestamp getUse_time() {
		return use_time;
	}
	public void setUse_time(Timestamp use_time) {
		this.use_time = use_time;
	}
	public Timestamp getBack_time() {
		return back_time;
	}
	public void setBack_time(Timestamp back_time) {
		this.back_time = back_time;
	}
	
}
