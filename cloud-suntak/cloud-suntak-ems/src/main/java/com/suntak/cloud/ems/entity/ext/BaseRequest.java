package com.suntak.cloud.ems.entity.ext;
/** 
 * @Package com.suntak.cloud.ems.entity.ext 
 * @Description: TODO
 * @date Jul 27, 2019 2:32:04 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class BaseRequest {
    private Integer org_id; 
    private Integer pageNum;
    private Integer pageSize;
    private String keyword;
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
