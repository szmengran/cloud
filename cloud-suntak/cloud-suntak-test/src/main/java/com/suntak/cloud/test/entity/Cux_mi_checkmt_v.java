package com.suntak.cloud.test.entity;

import java.sql.Timestamp;

/**
 * @Package com.suntak.cloud.test.entity
 * @Description: 冶具信息
 * @date 2018年10月16日 上午11:14:13
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Cux_mi_checkmt_v {
	private String organization_id;
	private String item_rev; //版本号
	private String check_type; //治具类型
	private String check_status; //治具状态
	private String check_stru; //治具结构
	private Timestamp make_date; //制作日期
	private String check_memo; //备注
	public String getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}
	public String getItem_rev() {
		return item_rev;
	}
	public void setItem_rev(String item_rev) {
		this.item_rev = item_rev;
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
	public String getCheck_stru() {
		return check_stru;
	}
	public void setCheck_stru(String check_stru) {
		this.check_stru = check_stru;
	}
	public Timestamp getMake_date() {
		return make_date;
	}
	public void setMake_date(Timestamp make_date) {
		this.make_date = make_date;
	}
	public String getCheck_memo() {
		return check_memo;
	}
	public void setCheck_memo(String check_memo) {
		this.check_memo = check_memo;
	}
}
