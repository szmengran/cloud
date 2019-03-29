package com.suntak.cloud.oa.entity;

import com.szmengran.mybatis.utils.Table;

/** 
 * @Package com.suntak.cloud.oa.entity 
 * @Description: 个人学历及资格提升计划主表
 * @date Mar 28, 2019 3:54:49 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "form_id")
public class Cux_oa_personal_dev_plan_v {
    private String form_id            ;
    private String start_member       ;
    private String org_name           ;
    private String form_no            ;
    private String emp_code           ;
    private String emp_name           ;
    private String max_education      ;
    private String major              ;
    private String department_name    ;
    private String sub_department_name;
    private String member_post        ;
    private String target_education   ;
    private String target_major       ;
    private String target_leval       ;
    private String is_education_pass  ;
    private String is_major_pass      ;
    private String is_send_msg        ;
    private String member_sign        ;
    public String getForm_id() {
        return form_id;
    }
    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }
    public String getStart_member() {
        return start_member;
    }
    public void setStart_member(String start_member) {
        this.start_member = start_member;
    }
    public String getOrg_name() {
        return org_name;
    }
    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }
    public String getForm_no() {
        return form_no;
    }
    public void setForm_no(String form_no) {
        this.form_no = form_no;
    }
    public String getEmp_code() {
        return emp_code;
    }
    public void setEmp_code(String emp_code) {
        this.emp_code = emp_code;
    }
    public String getEmp_name() {
        return emp_name;
    }
    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }
    public String getMax_education() {
        return max_education;
    }
    public void setMax_education(String max_education) {
        this.max_education = max_education;
    }
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    public String getDepartment_name() {
        return department_name;
    }
    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
    public String getSub_department_name() {
        return sub_department_name;
    }
    public void setSub_department_name(String sub_department_name) {
        this.sub_department_name = sub_department_name;
    }
    public String getMember_post() {
        return member_post;
    }
    public void setMember_post(String member_post) {
        this.member_post = member_post;
    }
    public String getTarget_education() {
        return target_education;
    }
    public void setTarget_education(String target_education) {
        this.target_education = target_education;
    }
    public String getTarget_major() {
        return target_major;
    }
    public void setTarget_major(String target_major) {
        this.target_major = target_major;
    }
    public String getTarget_leval() {
        return target_leval;
    }
    public void setTarget_leval(String target_leval) {
        this.target_leval = target_leval;
    }
    public String getIs_education_pass() {
        return is_education_pass;
    }
    public void setIs_education_pass(String is_education_pass) {
        this.is_education_pass = is_education_pass;
    }
    public String getIs_major_pass() {
        return is_major_pass;
    }
    public void setIs_major_pass(String is_major_pass) {
        this.is_major_pass = is_major_pass;
    }
    public String getIs_send_msg() {
        return is_send_msg;
    }
    public void setIs_send_msg(String is_send_msg) {
        this.is_send_msg = is_send_msg;
    }
    public String getMember_sign() {
        return member_sign;
    }
    public void setMember_sign(String member_sign) {
        this.member_sign = member_sign;
    }
}
