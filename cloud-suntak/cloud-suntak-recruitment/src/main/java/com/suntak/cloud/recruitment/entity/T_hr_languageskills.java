package com.suntak.cloud.recruitment.entity;
/**
 * @Package com.suntak.cloud.interview.entity
 * @Description: 语言能力
 * @date 2018年7月19日 上午9:21:48
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_hr_languageskills {
	private Integer applicantid;
	private String mandarin    ;
	private String english     ;
	private String japanese    ;
	private String other       ;
	public Integer getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(Integer applicantid) {
		this.applicantid = applicantid;
	}
	public String getMandarin() {
		return mandarin;
	}
	public void setMandarin(String mandarin) {
		this.mandarin = mandarin;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getJapanese() {
		return japanese;
	}
	public void setJapanese(String japanese) {
		this.japanese = japanese;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
}
