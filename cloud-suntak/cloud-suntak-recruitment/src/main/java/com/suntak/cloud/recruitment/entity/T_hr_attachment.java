package com.suntak.cloud.recruitment.entity;

import java.sql.Timestamp;

/**
 * @Package com.suntak.cloud.interview.entity
 * @Description: 应聘者附近
 * @date 2018年7月19日 上午9:09:05
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_hr_attachment {
	private String applicantid   ;
	private String idcard        ;
	private String diploma       ;
	private String medicalreport ;
	private String avatar        ;
	private Timestamp createstamp;
	private Timestamp updatestamp;
	public String getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(String applicantid) {
		this.applicantid = applicantid;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getDiploma() {
		return diploma;
	}
	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}
	public String getMedicalreport() {
		return medicalreport;
	}
	public void setMedicalreport(String medicalreport) {
		this.medicalreport = medicalreport;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Timestamp getCreatestamp() {
		return createstamp;
	}
	public void setCreatestamp(Timestamp createstamp) {
		this.createstamp = createstamp;
	}
	public Timestamp getUpdatestamp() {
		return updatestamp;
	}
	public void setUpdatestamp(Timestamp updatestamp) {
		this.updatestamp = updatestamp;
	}
	
}
