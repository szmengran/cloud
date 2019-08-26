package com.suntak.cloud.ems.service;

import java.util.List;

import com.suntak.cloud.ems.entity.Ems_dm_maintain;
import com.suntak.cloud.ems.entity.Ems_dm_maintain_content;

/** 
 * @Package com.suntak.cloud.ems.service 
 * @Description: 保养单明细内容服务
 * @date Aug 26, 2019 10:07:00 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface MaintainContentService {

    /**
     * 查找保养单明细内容
     * @param ems_dm_maintain
     * @return
     * @throws Exception      
     * @return: List<Ems_dm_maintain_content>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    List<Ems_dm_maintain_content> findMaintainContent(Ems_dm_maintain ems_dm_maintain) throws Exception;
}
