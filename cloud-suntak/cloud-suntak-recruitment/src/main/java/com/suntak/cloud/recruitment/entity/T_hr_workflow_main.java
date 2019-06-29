package com.suntak.cloud.recruitment.entity;

import com.szmengran.mybatis.utils.Table;

/**
 * @Package com.suntak.cloud.recruitment.entity
 * @Description: 工作流程
 * @date 2018年8月17日 下午4:38:38
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "workflowid")
public class T_hr_workflow_main {
	private Integer workflowid;
	private String name;
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
}
