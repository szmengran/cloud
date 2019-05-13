package com.suntak.cloud.ems.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/** 
 * @Package com.suntak.cloud.ems.entity 
 * @Description: 人员信息
 * @date Mar 26, 2019 3:06:01 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Oz_org_userinfo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 7836141733328396525L;
    private Integer id            ;
    private Integer ouinfo_id     ;
    private String name          ;
    private String name_pinyin   ;
    private String login_name    ;
    private String userinfo_title;
    private String order_no      ;
    private Integer status        ;
    private String is_default    ;
    private Integer jobtitle_id   ;
    private Integer joblevel_id   ;
    private String mobile        ;
    private String telephone     ;
    private String email         ;
    private String homeno        ;
    private String faxno         ;
    private String address       ;
    private String zip_code      ;
    private String office        ;
    private String card_id       ;
    private String gender        ;
    private Timestamp birthday      ;
    private Timestamp work_date     ;
    private String employer_id   ;
    private Timestamp employed_date ;
    private String memos         ;
    private String third_id      ;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getOuinfo_id() {
        return ouinfo_id;
    }
    public void setOuinfo_id(Integer ouinfo_id) {
        this.ouinfo_id = ouinfo_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName_pinyin() {
        return name_pinyin;
    }
    public void setName_pinyin(String name_pinyin) {
        this.name_pinyin = name_pinyin;
    }
    public String getLogin_name() {
        return login_name;
    }
    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }
    public String getUserinfo_title() {
        return userinfo_title;
    }
    public void setUserinfo_title(String userinfo_title) {
        this.userinfo_title = userinfo_title;
    }
    public String getOrder_no() {
        return order_no;
    }
    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getIs_default() {
        return is_default;
    }
    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }
    public Integer getJobtitle_id() {
        return jobtitle_id;
    }
    public void setJobtitle_id(Integer jobtitle_id) {
        this.jobtitle_id = jobtitle_id;
    }
    public Integer getJoblevel_id() {
        return joblevel_id;
    }
    public void setJoblevel_id(Integer joblevel_id) {
        this.joblevel_id = joblevel_id;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getHomeno() {
        return homeno;
    }
    public void setHomeno(String homeno) {
        this.homeno = homeno;
    }
    public String getFaxno() {
        return faxno;
    }
    public void setFaxno(String faxno) {
        this.faxno = faxno;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getZip_code() {
        return zip_code;
    }
    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }
    public String getOffice() {
        return office;
    }
    public void setOffice(String office) {
        this.office = office;
    }
    public String getCard_id() {
        return card_id;
    }
    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Timestamp getBirthday() {
        return birthday;
    }
    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }
    public Timestamp getWork_date() {
        return work_date;
    }
    public void setWork_date(Timestamp work_date) {
        this.work_date = work_date;
    }
    public String getEmployer_id() {
        return employer_id;
    }
    public void setEmployer_id(String employer_id) {
        this.employer_id = employer_id;
    }
    public Timestamp getEmployed_date() {
        return employed_date;
    }
    public void setEmployed_date(Timestamp employed_date) {
        this.employed_date = employed_date;
    }
    public String getMemos() {
        return memos;
    }
    public void setMemos(String memos) {
        this.memos = memos;
    }
    public String getThird_id() {
        return third_id;
    }
    public void setThird_id(String third_id) {
        this.third_id = third_id;
    }
}
