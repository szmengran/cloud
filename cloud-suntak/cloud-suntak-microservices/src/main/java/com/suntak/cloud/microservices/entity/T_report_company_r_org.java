package com.suntak.cloud.microservices.entity;
/** 
 * @Package com.suntak.cloud.microservices.entity 
 * @Description: HR部门和ERP部门映射表
 * @date Apr 30, 2019 9:55:08 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_report_company_r_org {
    private Integer org_id;
    private String companycode;
    private String companyname;
    public Integer getOrg_id() {
        return org_id;
    }
    public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
    }
    public String getCompanycode() {
        return companycode;
    }
    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }
    public String getCompanyname() {
        return companyname;
    }
    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
    
}
