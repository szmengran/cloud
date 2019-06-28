package com.suntak.autotask.bean;

/**
 * 集中付款申请表配置类 - 20190215
 * @author zzhong
 *
 */
public class PaymentConfigBean {
    
    private String orgId;//发单组织ID
    private String templateNo;//模板编号
    private String mon;//月旬ID
    private String monItem;//月旬项
    private String submitDay;//发单日
    private String submitMember;//发单人ID
    private String submitMemberName;//发单人
    private String submitMemberLoginName;//发单人登录名
    private String connectedOrgId;//关联组织（一起发单）
    
    
    
    
    public String getTemplateNo() {
        return templateNo;
    }
    public void setTemplateNo(String templateNo) {
        this.templateNo = templateNo;
    }
    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public String getMon() {
        return mon;
    }
    public void setMon(String mon) {
        this.mon = mon;
    }
    public String getMonItem() {
        return monItem;
    }
    public void setMonItem(String monItem) {
        this.monItem = monItem;
    }
    public String getSubmitDay() {
        return submitDay;
    }
    public void setSubmitDay(String submitDay) {
        this.submitDay = submitDay;
    }
    public String getSubmitMember() {
        return submitMember;
    }
    public void setSubmitMember(String submitMember) {
        this.submitMember = submitMember;
    }

    public String getSubmitMemberName() {
        return submitMemberName;
    }
    public void setSubmitMemberName(String submitMemberName) {
        this.submitMemberName = submitMemberName;
    }
    public String getSubmitMemberLoginName() {
        return submitMemberLoginName;
    }
    public void setSubmitMemberLoginName(String submitMemberLoginName) {
        this.submitMemberLoginName = submitMemberLoginName;
    }
    public String getConnectedOrgId() {
        return connectedOrgId;
    }
    public void setConnectedOrgId(String connectedOrgId) {
        this.connectedOrgId = connectedOrgId;
    }
    
}
