package com.suntak.cloud.recruitment.entity;

import com.szmengran.mybatis.utils.GeneratedValue;
import com.szmengran.mybatis.utils.GenerationType;
import com.szmengran.mybatis.utils.Table;

/**
 * @Package com.suntak.cloud.interview.entity
 * @Description: 家庭成员
 * @date 2018年7月19日 上午9:15:21
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "familymemberid")
public class T_hr_familymember {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_t_hr_familymember")
	private Integer familymemberid;
	private String applicantid    ;
	private String name           ;
	private String relationship   ;
	private String career         ;
	private String address        ;
	private String phone          ;
	public Integer getFamilymemberid() {
		return familymemberid;
	}
	public void setFamilymemberid(Integer familymemberid) {
		this.familymemberid = familymemberid;
	}
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
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
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
