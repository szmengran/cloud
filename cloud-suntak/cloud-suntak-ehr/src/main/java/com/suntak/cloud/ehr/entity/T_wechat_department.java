package com.suntak.cloud.ehr.entity;

import com.szmengran.mybatis.utils.Table;

/**
 * @Package com.suntak.cloud.ehr.entity
 * @Description: TODO
 * @date Dec 17, 2018 11:09:15 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "id")
public class T_wechat_department {
	private Integer id;
	private String name;
	private Integer parentid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
}
