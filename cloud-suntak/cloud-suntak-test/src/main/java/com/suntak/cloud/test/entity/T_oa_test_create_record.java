package com.suntak.cloud.test.entity;

import java.sql.Timestamp;

/**
 * @Package com.suntak.cloud.test.entity
 * @Description: 制作记录
 * @date 2018年6月25日 上午11:40:35
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_oa_test_create_record {
	private Integer create_record_id;
	private Integer wip_entity_id;
	private String operation_code;
	private String empcode;
	private String empname;
	private Timestamp create_time;
	private Timestamp finish_time;
	public Integer getCreate_record_id() {
		return create_record_id;
	}
	public void setCreate_record_id(Integer create_record_id) {
		this.create_record_id = create_record_id;
	}
	public Integer getWip_entity_id() {
		return wip_entity_id;
	}
	public void setWip_entity_id(Integer wip_entity_id) {
		this.wip_entity_id = wip_entity_id;
	}
	public String getOperation_code() {
		return operation_code;
	}
	public void setOperation_code(String operation_code) {
		this.operation_code = operation_code;
	}
	public String getEmpcode() {
		return empcode;
	}
	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getFinish_time() {
		return finish_time;
	}
	public void setFinish_time(Timestamp finish_time) {
		this.finish_time = finish_time;
	}
	
}
