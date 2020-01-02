package com.suntak.push.entity;

import java.io.Serializable;

import com.szmengran.mybatis.utils.Table;

@Table(name = "cux.cux_soa_wip_push")
public class CuxSoaWipPush extends Push implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7319332030400208765L;
	private Integer organization_id;
	private String department_code;
	private String department_name;
	private String operation_code;
	private String operation_name;
	private String order_status;
	private String job_status;
	private String wip_entity_name;
	private String wip_qty;
	private String wip_unit;
	private String pnl_cp_area;
	private String data_type;
	public Integer getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(Integer organization_id) {
		this.organization_id = organization_id;
	}
	public String getDepartment_code() {
		return department_code;
	}
	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getOperation_code() {
		return operation_code;
	}
	public void setOperation_code(String operation_code) {
		this.operation_code = operation_code;
	}
	public String getOperation_name() {
		return operation_name;
	}
	public void setOperation_name(String operation_name) {
		this.operation_name = operation_name;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getJob_status() {
		return job_status;
	}
	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}
	public String getWip_entity_name() {
		return wip_entity_name;
	}
	public void setWip_entity_name(String wip_entity_name) {
		this.wip_entity_name = wip_entity_name;
	}
	public String getWip_qty() {
		return wip_qty;
	}
	public void setWip_qty(String wip_qty) {
		this.wip_qty = wip_qty;
	}
	public String getWip_unit() {
		return wip_unit;
	}
	public void setWip_unit(String wip_unit) {
		this.wip_unit = wip_unit;
	}
	public String getPnl_cp_area() {
		return pnl_cp_area;
	}
	public void setPnl_cp_area(String pnl_cp_area) {
		this.pnl_cp_area = pnl_cp_area;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	
}
