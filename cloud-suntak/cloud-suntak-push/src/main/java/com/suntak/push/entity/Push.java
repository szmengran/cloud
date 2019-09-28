package com.suntak.push.entity;

import java.sql.Timestamp;

public class Push {
	
	private Integer organization_id;
	private String organization_name;
	private String order_no;
	private String item_no;
	private Float stop_time;
	private Timestamp in_time;
	private String hold_memo;
	private String push_mark;
	private Timestamp push_date;
	private String attribute_category;
	private String attribute1;
	private String attribute2;
	private String attribute30;
	private Timestamp last_update_date;
	private String last_updated_by;
	private Timestamp creation_date;
	private String created_by;
	private String last_update_login;
	public Integer getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(Integer organization_id) {
		this.organization_id = organization_id;
	}
	public String getOrganization_name() {
		return organization_name;
	}
	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getItem_no() {
		return item_no;
	}
	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	public Float getStop_time() {
		return stop_time;
	}
	public void setStop_time(Float stop_time) {
		this.stop_time = stop_time;
	}
	public Timestamp getIn_time() {
		return in_time;
	}
	public void setIn_time(Timestamp in_time) {
		this.in_time = in_time;
	}
	public String getHold_memo() {
		return hold_memo;
	}
	public void setHold_memo(String hold_memo) {
		this.hold_memo = hold_memo;
	}
	public String getPush_mark() {
		return push_mark;
	}
	public void setPush_mark(String push_mark) {
		this.push_mark = push_mark;
	}
	public Timestamp getPush_date() {
		return push_date;
	}
	public void setPush_date(Timestamp push_date) {
		this.push_date = push_date;
	}
	public String getAttribute_category() {
		return attribute_category;
	}
	public void setAttribute_category(String attribute_category) {
		this.attribute_category = attribute_category;
	}
	public String getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	public String getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	public String getAttribute30() {
		return attribute30;
	}
	public void setAttribute30(String attribute30) {
		this.attribute30 = attribute30;
	}
	public Timestamp getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(Timestamp last_update_date) {
		this.last_update_date = last_update_date;
	}
	public String getLast_updated_by() {
		return last_updated_by;
	}
	public void setLast_updated_by(String last_updated_by) {
		this.last_updated_by = last_updated_by;
	}
	public Timestamp getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Timestamp creation_date) {
		this.creation_date = creation_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getLast_update_login() {
		return last_update_login;
	}
	public void setLast_update_login(String last_update_login) {
		this.last_update_login = last_update_login;
	}

}
