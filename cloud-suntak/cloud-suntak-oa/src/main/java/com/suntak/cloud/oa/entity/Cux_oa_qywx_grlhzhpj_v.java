package com.suntak.cloud.oa.entity;

import com.szmengran.mybatis.utils.Table;

/** 
 * @Package com.suntak.cloud.oa.entity 
 * @Description: 个人量化综合评价表
 * @date Feb 25, 2019 11:34:03 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "id")
public class Cux_oa_qywx_grlhzhpj_v {
	private String id;
	private String l_factory;
	private String l_dept;
	private String l_cla;
	private String l_date;
	private String l_code;
	private String l_name; //姓名
	private String l_post; //职务
	private String l_rzrq; //入职日期
	private String l_zpljcs; //个人产量达成率计件奖金
	private String l_xldcl; //个人排名情况
	private String l_jcljcs; //个人强制排序后评价等级
	private String l_wgwjljcs; //个人对应最高加班工时
	private String l_hzysljcs; //个人备注
	private String l_tezt; //企业微信发送标志
	private String l_ygqz; //签名
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getL_rzrq() {
		return l_rzrq;
	}
	public void setL_rzrq(String l_rzrq) {
		this.l_rzrq = l_rzrq;
	}
	public String getL_zpljcs() {
		return l_zpljcs;
	}
	public void setL_zpljcs(String l_zpljcs) {
		this.l_zpljcs = l_zpljcs;
	}
	public String getL_xldcl() {
		return l_xldcl;
	}
	public void setL_xldcl(String l_xldcl) {
		this.l_xldcl = l_xldcl;
	}
	public String getL_jcljcs() {
		return l_jcljcs;
	}
	public void setL_jcljcs(String l_jcljcs) {
		this.l_jcljcs = l_jcljcs;
	}
	public String getL_wgwjljcs() {
		return l_wgwjljcs;
	}
	public void setL_wgwjljcs(String l_wgwjljcs) {
		this.l_wgwjljcs = l_wgwjljcs;
	}
	public String getL_hzysljcs() {
		return l_hzysljcs;
	}
	public void setL_hzysljcs(String l_hzysljcs) {
		this.l_hzysljcs = l_hzysljcs;
	}
	public String getL_tezt() {
		return l_tezt;
	}
	public void setL_tezt(String l_tezt) {
		this.l_tezt = l_tezt;
	}
	public String getL_ygqz() {
		return l_ygqz;
	}
	public void setL_ygqz(String l_ygqz) {
		this.l_ygqz = l_ygqz;
	}
}
