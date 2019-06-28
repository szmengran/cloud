package com.suntak.autotask.bean;

/**
 * 支付信息类 - 20190216
 * @author zzhong
 *
 */
public class PaymentInfoBean {
    private String orgId;//组织ID
    private String orgName;//组织名
    private String formNo;//单据编号
    private String payMan;//报销人/供应商
    private String payDate;//支付日期
    private String currency;//币别
    private String originalPrice;//原币
    private String cnyPrice;//人民币
    private String formType;//表单类型
    private String payReason;//支付原因
    private String summaryId;
    
    
    
    
    
    
    
    public String getSummaryId() {
        return summaryId;
    }
    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }
    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public String getOrgName() {
        return orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getFormNo() {
        return formNo;
    }
    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }
    public String getPayMan() {
        return payMan;
    }
    public void setPayMan(String payMan) {
        this.payMan = payMan;
    }
    public String getPayDate() {
        return payDate;
    }
    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getOriginalPrice() {
        return originalPrice;
    }
    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }
    public String getCnyPrice() {
        return cnyPrice;
    }
    public void setCnyPrice(String cnyPrice) {
        this.cnyPrice = cnyPrice;
    }
    public String getFormType() {
        return formType;
    }
    public void setFormType(String formType) {
        this.formType = formType;
    }
    public String getPayReason() {
        return payReason;
    }
    public void setPayReason(String payReason) {
        this.payReason = payReason;
    }
    
    
}
