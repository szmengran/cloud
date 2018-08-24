package com.suntak.cloud.recruitment.entity;
/**
 * @Package com.suntak.cloud.recruitment.entity
 * @Description: 子流程
 * @date 2018年8月22日 上午8:58:52
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_hr_workflow_sub {
	private Integer subflowid;
	private Integer workflowid;
	private String name;
	private String currentrole;
	private String nextrole;
	private String url;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrentrole() {
		return currentrole;
	}
	public void setCurrentrole(String currentrole) {
		this.currentrole = currentrole;
	}
	public String getNextrole() {
		return nextrole;
	}
	public void setNextrole(String nextrole) {
		this.nextrole = nextrole;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
