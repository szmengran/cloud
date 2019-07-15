package com.suntak.cloud.ems.service;

import java.util.List;

import com.suntak.cloud.ems.entity.Ems_dm_equipment_details;

/** 
 * @Package com.suntak.cloud.ems.service 
 * @Description: 设备明细服务
 * @date Mar 26, 2019 5:11:00 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface EquipmentDetailService {

    /**
     * 查找设备明细信息
     * @param useD
     * @param procedure
     * @param keyword
     * @param org_id
     * @return
     * @throws Exception      
     * @return: List<Ems_dm_equipment_details>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    List<Ems_dm_equipment_details> findEquipmentDetail(String useD, String procedure, 
            String keyword, Integer org_id) throws Exception;
}
