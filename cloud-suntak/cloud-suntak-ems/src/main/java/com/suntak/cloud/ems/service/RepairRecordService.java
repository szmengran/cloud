package com.suntak.cloud.ems.service;

import com.suntak.cloud.ems.entity.RepairRecordRequestBody;

/** 
 * @Package com.suntak.cloud.ems.service 
 * @Description: 维修记录服务
 * @date Mar 18, 2019 10:43:23 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface RepairRecordService {
    
    /**
     * 保存维修记录已经配件更换信息
     * @param repairRecordRequestBody
     * @return
     * @throws Exception      
     * @return: Boolean      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    Boolean insert(RepairRecordRequestBody repairRecordRequestBody) throws Exception;
}
