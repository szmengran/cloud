package com.suntak.cloud.recruitment.entity;
/**
 * @Package com.suntak.cloud.interview.entity
 * @Description: 受教育历史
 * @date 2018年7月19日 上午9:13:37
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_hr_educationhistory {
	private Integer educationhistoryid;
	private String applicantid        ;
	private String school             ;
	private String profession         ;
	private String certificate        ;
	public Integer getEducationhistoryid() {
		return educationhistoryid;
	}
	public void setEducationhistoryid(Integer educationhistoryid) {
		this.educationhistoryid = educationhistoryid;
	}
	public String getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(String applicantid) {
		this.applicantid = applicantid;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	
}