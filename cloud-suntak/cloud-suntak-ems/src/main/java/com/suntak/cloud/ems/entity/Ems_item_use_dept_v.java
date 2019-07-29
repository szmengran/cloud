package com.suntak.cloud.ems.entity;
/** 
 * @Package com.suntak.cloud.ems.entity 
 * @Description: TODO
 * @date Jul 23, 2019 1:25:16 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Ems_item_use_dept_v {
    private Integer disposition_id;
    private Integer organization_id;
    private String segment1;
    private String dist;
    private Integer distribution_account;
    public Integer getDisposition_id() {
        return disposition_id;
    }
    public void setDisposition_id(Integer disposition_id) {
        this.disposition_id = disposition_id;
    }
    public Integer getOrganization_id() {
        return organization_id;
    }
    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }
    public String getSegment1() {
        return segment1;
    }
    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }
    public String getDist() {
        return dist;
    }
    public void setDist(String dist) {
        this.dist = dist;
    }
    public Integer getDistribution_account() {
        return distribution_account;
    }
    public void setDistribution_account(Integer distribution_account) {
        this.distribution_account = distribution_account;
    }
    
}
