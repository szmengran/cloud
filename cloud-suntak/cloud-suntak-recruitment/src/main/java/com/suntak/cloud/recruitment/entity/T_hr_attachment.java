package com.suntak.cloud.recruitment.entity;

import java.sql.Timestamp;

import com.szmengran.mybatis.utils.Table;

/**
 * @Package com.suntak.cloud.interview.entity
 * @Description: 应聘者附近
 * @date 2018年7月19日 上午9:09:05
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "applicantid")
public class T_hr_attachment {
	private String applicantid   ;
	private String idcardpositive        ;
	private String idcardnegative        ;
	private String diploma       ;
	private String medicalreport ;
	private String othercertificate1 ;
	private String othercertificate2 ;
	private String avatar        ;
	private Timestamp createstamp;
	private Timestamp updatestamp;
    public String getApplicantid() {
        return applicantid;
    }
    public void setApplicantid(String applicantid) {
        this.applicantid = applicantid;
    }
    public String getIdcardpositive() {
        return idcardpositive;
    }
    public void setIdcardpositive(String idcardpositive) {
        this.idcardpositive = idcardpositive;
    }
    public String getIdcardnegative() {
        return idcardnegative;
    }
    public void setIdcardnegative(String idcardnegative) {
        this.idcardnegative = idcardnegative;
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
    public String getOthercertificate1() {
        return othercertificate1;
    }
    public void setOthercertificate1(String othercertificate1) {
        this.othercertificate1 = othercertificate1;
    }
    public String getOthercertificate2() {
        return othercertificate2;
    }
    public void setOthercertificate2(String othercertificate2) {
        this.othercertificate2 = othercertificate2;
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
