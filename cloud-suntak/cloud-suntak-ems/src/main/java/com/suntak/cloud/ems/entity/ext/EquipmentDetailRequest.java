package com.suntak.cloud.ems.entity.ext;
/** 
 * @Package com.suntak.cloud.ems.entity.ext 
 * @Description: 设备查询请求实体
 * @date Mar 27, 2019 1:10:40 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class EquipmentDetailRequest {
    private String useD;
    private String procedure;
    private Integer org_id; 
    private Integer pageNum;
    private Integer pageSize;
    private String keyword;
    public String getUseD() {
        return useD;
    }
    public void setUseD(String useD) {
        this.useD = useD;
    }
    public String getProcedure() {
        return procedure;
    }
    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
    public Integer getOrg_id() {
        return org_id;
    }
    public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
    }
    public Integer getPageNum() {
        return pageNum;
    }
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
