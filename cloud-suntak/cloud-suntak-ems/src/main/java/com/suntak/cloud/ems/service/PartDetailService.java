package com.suntak.cloud.ems.service;

import java.util.List;

import com.suntak.cloud.ems.entity.Ems_partdetail_v;

/** 
 * @Package com.suntak.cloud.ems.service 
 * @Description: 配件信息
 * @date Mar 25, 2019 3:20:31 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface PartDetailService {
    
    /**
     * 查找配件信息
     * @param keyword
     * @param org_id
     * @return
     * @throws Exception      
     * @return: List<Ems_partdetail_v>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    List<Ems_partdetail_v> findPartInfo(String keyword, Integer org_id) throws Exception;
    
}
