package com.suntak.cloud.ems.service;

import java.util.List;

import com.suntak.cloud.ems.entity.RepairRecordRequestBody;
import com.suntak.cloud.ems.entity.ext.Ems_dm_repair_record_ext;

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
    
    /**
     * 查找维修记录
     * @param userid
     * @param keyword
     * @return
     * @throws Exception      
     * @return: List<Ems_dm_repair_record_ext>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    List<Ems_dm_repair_record_ext> findRepairRecord(Integer userid, String keyword) throws Exception;
    
    /**
     * 查找生产维修申报记录
     * @param empcode
     * @param keyword
     * @return
     * @throws Exception      
     * @return: List<Ems_dm_repair_record_ext>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    List<Ems_dm_repair_record_ext> findRepairRecordProd(String empcode, String keyword) throws Exception;
}
