package com.suntak.cloud.ems.service;

import java.util.List;

import com.suntak.cloud.ems.entity.Ems_dm_maintain;
import com.suntak.cloud.ems.entity.Ems_dm_maintain_content;

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
     * @param userid
     * @param id
     * @return
     * @throws Exception      
     * @return: List<Ems_dm_maintain>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    List<Ems_dm_maintain> findMaintain(Integer organization_id, String keyword, Integer userid, Integer id) throws Exception;
    
    /**
     * 保存或更新保养单信息
     * @param empcode
     * @param maintain
     * @param maintainContent
     * @throws Exception      
     * @return: void      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    void saveOrUpdate(String empcode, Ems_dm_maintain maintain, Ems_dm_maintain_content[] maintainContent) throws Exception;
}
