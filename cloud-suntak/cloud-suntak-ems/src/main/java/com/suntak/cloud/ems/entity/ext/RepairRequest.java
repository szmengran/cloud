package com.suntak.cloud.ems.entity.ext;
/** 
 * @Package com.suntak.cloud.ems.entity.ext 
 * @Description: 维修记录查询实体
 * @date May 17, 2019 11:43:18 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class RepairRequest {

    private String keyword;
    private Integer pageNum;
    private Integer pageSize;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
    
}
