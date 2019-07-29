package com.suntak.cloud.ems.service;

import java.util.List;

import com.suntak.cloud.ems.entity.Ems_secondary_inventories_v;

/** 
 * @Package com.suntak.cloud.ems.service 
 * @Description: 来源库服务
 * @date Jul 23, 2019 2:46:25 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface EmsSecondaryInventoriesService {

    /**
     * 来源库信息查询
     * @param organization_id
     * @return
     * @throws Exception      
     * @return: List<Ems_secondary_inventories_v>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    List<Ems_secondary_inventories_v> findInventories(Integer organization_id) throws Exception;
}
