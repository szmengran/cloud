package com.suntak.cloud.test.entity;

import java.sql.Timestamp;

/**
 * @Package com.suntak.cloud.test.entity
 * @Description: 资料制作报表
 * @date 2018年6月25日 上午11:38:15
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Cux_soa_ft_data_v {
	private Integer wip_entity_id;
	private Integer organization_id;
	private Integer pnl_qty;
	private String operation_code;
	private String segment1;
	private String item_rev;
	private String dept;
	private String check_type;
	private String check_status;
	private Timestamp inv_date;
	public Integer getWip_entity_id() {
		return wip_entity_id;
	}
	public void setWip_entity_id(Integer wip_entity_id) {
		this.wip_entity_id = wip_entity_id;
	}
	public Integer getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(Integer organization_id) {
		this.organization_id = organization_id;
	}
	public Integer getPnl_qty() {
		return pnl_qty;
	}
	public void setPnl_qty(Integer pnl_qty) {
		this.pnl_qty = pnl_qty;
	}
	public String getOperation_code() {
		return operation_code;
	}
	public void setOperation_code(String operation_code) {
		this.operation_code = operation_code;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public String getItem_rev() {
		return item_rev;
	}
	public void setItem_rev(String item_rev) {
		this.item_rev = item_rev;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getCheck_type() {
		return check_type;
	}
	public void setCheck_type(String check_type) {
		this.check_type = check_type;
	}
	public String getCheck_status() {
		return check_status;
	}
	public void setCheck_status(String check_status) {
		this.check_status = check_status;
	}
	public Timestamp getInv_date() {
		return inv_date;
	}
	public void setInv_date(Timestamp inv_date) {
		this.inv_date = inv_date;
	}
}
