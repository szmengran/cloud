package com.suntak.cloud.recruitment.entity;

import com.szmengran.mybatis.utils.Table;

/**
 * @Package com.suntak.cloud.recruitment.entity
 * @Description: 子流程
 * @date 2018年8月22日 上午8:58:52
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "subflowid")
public class T_hr_workflow_sub {
	private Integer subflowid;
	private Integer workflowid;
	private String subflowname;
	private String role;
	private String preflowid;
	private String url;
	private Integer agree;
	public Integer getSubflowid() {
		return subflowid;
	}
	public void setSubflowid(Integer subflowid) {
		this.subflowid = subflowid;
	}
	public Integer getWorkflowid() {
		return workflowid;
	}
	public void setWorkflowid(Integer workflowid) {
		this.workflowid = workflowid;
	}
	public String getSubflowname() {
		return subflowname;
	}
	public void setSubflowname(String subflowname) {
		this.subflowname = subflowname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPreflowid() {
		return preflowid;
	}
	public void setPreflowid(String preflowid) {
		this.preflowid = preflowid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getAgree() {
		return agree;
	}
	public void setAgree(Integer agree) {
		this.agree = agree;
	}
	
}
