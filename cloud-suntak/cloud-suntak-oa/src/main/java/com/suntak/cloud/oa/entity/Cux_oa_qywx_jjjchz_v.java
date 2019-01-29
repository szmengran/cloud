package com.suntak.cloud.oa.entity;

import com.szmengran.mybatis.utils.Table;

/**
 * @Package com.suntak.cloud.oa.entity
 * @Description: 经济奖惩汇总表
 * @date Jan 28, 2019 9:00:10 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "id")
public class Cux_oa_qywx_jjjchz_v {
	private String id;
	private String formmain_id;
	private String l_factory;
	private String l_dept;
	private String l_cla;
	private String l_date;
	private String l_code;
	private String l_name;
	private String l_post;
	private String l_ds;
	private String l_jcje;
	private String l_ygqz;
	private String l_bz;
	private String l_tezt;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFormmain_id() {
		return formmain_id;
	}
	public void setFormmain_id(String formmain_id) {
		this.formmain_id = formmain_id;
	}
	public String getL_factory() {
		return l_factory;
	}
	public void setL_factory(String l_factory) {
		this.l_factory = l_factory;
	}
	public String getL_dept() {
		return l_dept;
	}
	public void setL_dept(String l_dept) {
		this.l_dept = l_dept;
	}
	public String getL_cla() {
		return l_cla;
	}
	public void setL_cla(String l_cla) {
		this.l_cla = l_cla;
	}
	public String getL_date() {
		return l_date;
	}
	public void setL_date(String l_date) {
		this.l_date = l_date;
	}
	public String getL_code() {
		return l_code;
	}
	public void setL_code(String l_code) {
		this.l_code = l_code;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	public String getL_post() {
		return l_post;
	}
	public void setL_post(String l_post) {
		this.l_post = l_post;
	}
	public String getL_ds() {
		return l_ds;
	}
	public void setL_ds(String l_ds) {
		this.l_ds = l_ds;
	}
	public String getL_jcje() {
		return l_jcje;
	}
	public void setL_jcje(String l_jcje) {
		this.l_jcje = l_jcje;
	}
	public String getL_ygqz() {
		return l_ygqz;
	}
	public void setL_ygqz(String l_ygqz) {
		this.l_ygqz = l_ygqz;
	}
	public String getL_bz() {
		return l_bz;
	}
	public void setL_bz(String l_bz) {
		this.l_bz = l_bz;
	}
	public String getL_tezt() {
		return l_tezt;
	}
	public void setL_tezt(String l_tezt) {
		this.l_tezt = l_tezt;
	}
}
