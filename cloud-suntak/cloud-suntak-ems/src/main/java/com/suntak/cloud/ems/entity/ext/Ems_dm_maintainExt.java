package com.suntak.cloud.ems.entity.ext;

import com.suntak.cloud.ems.entity.Ems_dm_maintain;

/** 
 * @Package com.suntak.cloud.ems.entity.ext 
 * @Description: TODO
 * @date Aug 26, 2019 11:40:48 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Ems_dm_maintainExt extends Ems_dm_maintain {
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
