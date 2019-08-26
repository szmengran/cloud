package com.suntak.cloud.ems.service;

import java.util.List;

import com.suntak.cloud.ems.entity.Ems_dm_repair_term_line;

/** 
 * @Package com.suntak.cloud.ems.service 
 * @Description: 保养单行信息服务
 * @date Aug 26, 2019 8:39:33 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface EmsDmRepairTermLineService {
    
    /**
     * 保养单行信息查询
     * @param equipment_name
     * @param maintenance_level
     * @return
     * @throws Exception      
     * @return: List<Ems_dm_repair_term_line>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    List<Ems_dm_repair_term_line> findRepairTermLine(String equipment_name, String maintenance_level) throws Exception;
}
