package com.suntak.cloud.recruitment.entity;

import java.sql.Timestamp;

import com.szmengran.mybatis.utils.Table;

/** 
 * @Package com.suntak.cloud.recruitment.entity 
 * @Description: 简历
 * @date Jul 10, 2019 9:04:31 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "applicantid,resume")
public class T_hr_resume {
    private String applicantid;
    private String resume;
    private Timestamp createstamp;
    public String getApplicantid() {
        return applicantid;
    }
    public void setApplicantid(String applicantid) {
        this.applicantid = applicantid;
    }
    public String getResume() {
        return resume;
    }
    public void setResume(String resume) {
        this.resume = resume;
    }
    public Timestamp getCreatestamp() {
        return createstamp;
    }
    public void setCreatestamp(Timestamp createstamp) {
        this.createstamp = createstamp;
    }
}
