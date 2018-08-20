package com.suntak.cloud.recruitment.entity;
/**
 * @Package com.suntak.cloud.recruitment.entity
 * @Description: 工作流程
 * @date 2018年8月17日 下午4:38:38
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_hr_workflow {
	private String workflow;
	private String currentrole;
	private String nextrole;
	private String remark;
	public String getWorkflow() {
		return workflow;
	}
	public void setWorkflow(String workflow) {
		this.workflow = workflow;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
