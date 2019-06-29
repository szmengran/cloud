package com.suntak.cloud.recruitment.entity;

import java.sql.Timestamp;

import com.szmengran.mybatis.utils.Table;

/**
 * @Package com.suntak.cloud.interview.entity
 * @Description: 应聘者基本信息
 * @date 2018年7月19日 上午8:50:17
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "applicantid")
public class T_hr_applicant {
	private String applicantid;
	private String ownerid;
	private String name;
	private String formername;
	private Integer sex;
	private Timestamp birthday;
	private String idcard;
	private String nativeplace;
	private Integer residencetype;
	private String nation;
	private Integer marrystatus;
	private Integer politicalstatus;
	private String avatar;
	private String phone;
	private String address;
	private String email;
	private Float height;
	private Float weight;
	private Float leftvision;
	private Float rightvision;
	private String education;
	private String profession;
	private Integer medicalhistory;
	private String medicalhistorydesc;
	private Integer crimehistory;
	private Integer pregnancy;
	private String jobtitle;
	private String hobby;
	private String relativesname        ;
	private String relativesdepartment  ;
	private String relativesposition    ;
	private String relativesrelationship;
	private String mandarin             ;
	private String english              ;
	private String japanese             ;
	private String other                ;
	private Timestamp createstamp;
	private Timestamp updatestamp;
	private Integer status;
	public String getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(String applicantid) {
		this.applicantid = applicantid;
	}
	public String getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFormername() {
		return formername;
	}
	public void setFormername(String formername) {
		this.formername = formername;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Timestamp getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getNativeplace() {
		return nativeplace;
	}
	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}
	public Integer getResidencetype() {
		return residencetype;
	}
	public void setResidencetype(Integer residencetype) {
		this.residencetype = residencetype;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public Integer getMarrystatus() {
		return marrystatus;
	}
	public void setMarrystatus(Integer marrystatus) {
		this.marrystatus = marrystatus;
	}
	public Integer getPoliticalstatus() {
		return politicalstatus;
	}
	public void setPoliticalstatus(Integer politicalstatus) {
		this.politicalstatus = politicalstatus;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	public Float getLeftvision() {
		return leftvision;
	}
	public void setLeftvision(Float leftvision) {
		this.leftvision = leftvision;
	}
	public Float getRightvision() {
		return rightvision;
	}
	public void setRightvision(Float rightvision) {
		this.rightvision = rightvision;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public Integer getMedicalhistory() {
		return medicalhistory;
	}
	public void setMedicalhistory(Integer medicalhistory) {
		this.medicalhistory = medicalhistory;
	}
	public String getMedicalhistorydesc() {
        return medicalhistorydesc;
    }
    public void setMedicalhistorydesc(String medicalhistorydesc) {
        this.medicalhistorydesc = medicalhistorydesc;
    }
    public Integer getCrimehistory() {
		return crimehistory;
	}
	public void setCrimehistory(Integer crimehistory) {
		this.crimehistory = crimehistory;
	}
	public Integer getPregnancy() {
		return pregnancy;
	}
	public void setPregnancy(Integer pregnancy) {
		this.pregnancy = pregnancy;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getRelativesname() {
		return relativesname;
	}
	public void setRelativesname(String relativesname) {
		this.relativesname = relativesname;
	}
	public String getRelativesdepartment() {
		return relativesdepartment;
	}
	public void setRelativesdepartment(String relativesdepartment) {
		this.relativesdepartment = relativesdepartment;
	}
	public String getRelativesposition() {
		return relativesposition;
	}
	public void setRelativesposition(String relativesposition) {
		this.relativesposition = relativesposition;
	}
	public String getRelativesrelationship() {
		return relativesrelationship;
	}
	public void setRelativesrelationship(String relativesrelationship) {
		this.relativesrelationship = relativesrelationship;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
