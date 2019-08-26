package com.suntak.cloud.ems.service;

import java.util.List;

import com.suntak.cloud.ems.entity.Ems_dm_maintain;

/** 
 * @Package com.suntak.cloud.ems.service 
 * @Description: TODO
 * @date Aug 26, 2019 9:40:59 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface EmsDmMaintainService {

    /**
     * 查找保养单信息
     * @param organization_id
     * @param keyword
     * @return
     * @throws Exception      
     * @return: List<Ems_dm_maintain>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    List<Ems_dm_maintain> findMaintain(Integer organization_id, String keyword) throws Exception;
}
