package com.suntak.cloud.ems.entity;
/** 
 * @Package com.suntak.cloud.ems.entity 
 * @Description: TODO
 * @date Jul 23, 2019 2:17:44 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Ems_secondary_inventories_v {
    private String code;
    private Integer organization_id;
    private String description;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Integer getOrganization_id() {
        return organization_id;
    }
    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
