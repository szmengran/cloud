package com.suntak.cloud.oa.entity;

import com.szmengran.mybatis.utils.Table;

/** 
 * @Package com.suntak.cloud.oa.entity 
 * @Description: TODO
 * @date May 13, 2019 4:51:57 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "id")
public class Cux_oa_qywx_aqscfx_v {
    private String id;
    private String l_date;
    private String formmain_id;
    private String l_factory;
    private String l_dept;
    private String l_cla;
    private String l_type; //员工级别
    private String l_no;
    private String l_code;
    private String l_name; //姓名
    private String l_post; //职务
    private String l_ygqz;
    private String l_tezt;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getL_date() {
        return l_date;
    }
    public void setL_date(String l_date) {
        this.l_date = l_date;
    }
    public String getFormmain_id() {
        return formmain_id;
    }
    public void setFormmain_id(String formmain_id) {
        this.formmain_id = formmain_id;
    }
    public String getL_factory() {
        return l_factory;
    }
    public void setL_factory(String l_factory) {
        this.l_factory = l_factory;
    }
    public String getL_dept() {
        return l_dept;
    }
    public void setL_dept(String l_dept) {
        this.l_dept = l_dept;
    }
    public String getL_cla() {
        return l_cla;
    }
    public void setL_cla(String l_cla) {
        this.l_cla = l_cla;
    }
    public String getL_type() {
        return l_type;
    }
    public void setL_type(String l_type) {
        this.l_type = l_type;
    }
    public String getL_no() {
        return l_no;
    }
    public void setL_no(String l_no) {
        this.l_no = l_no;
    }
    public String getL_code() {
        return l_code;
    }
    public void setL_code(String l_code) {
        this.l_code = l_code;
    }
    public String getL_name() {
        return l_name;
    }
    public void setL_name(String l_name) {
        this.l_name = l_name;
    }
    public String getL_post() {
        return l_post;
    }
    public void setL_post(String l_post) {
        this.l_post = l_post;
    }
    public String getL_ygqz() {
        return l_ygqz;
    }
    public void setL_ygqz(String l_ygqz) {
        this.l_ygqz = l_ygqz;
    }
    public String getL_tezt() {
        return l_tezt;
    }
    public void setL_tezt(String l_tezt) {
        this.l_tezt = l_tezt;
    }
    
}
