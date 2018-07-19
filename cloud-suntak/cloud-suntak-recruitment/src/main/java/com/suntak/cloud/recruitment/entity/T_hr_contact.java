package com.suntak.cloud.recruitment.entity;
/**
 * @Package com.suntak.cloud.interview.entity
 * @Description: 紧急联系人
 * @date 2018年7月19日 上午9:11:27
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_hr_contact {
	private String applicantid ;
	private String name        ;
	private String relationship;
	private String company     ;
	private String position    ;
	private String address     ;
	private String phone       ;
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
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
