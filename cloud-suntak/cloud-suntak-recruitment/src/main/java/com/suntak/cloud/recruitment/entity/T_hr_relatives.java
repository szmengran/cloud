package com.suntak.cloud.recruitment.entity;
/**
 * @Package com.suntak.cloud.interview.entity
 * @Description: 亲属
 * @date 2018年7月19日 上午9:24:01
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_hr_relatives {
	private String applicantid;
	private String name        ;
	private String department  ;
	private String position    ;
	private String relationship;
	public String getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(String applicantid) {
		this.applicantid = applicantid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
}
