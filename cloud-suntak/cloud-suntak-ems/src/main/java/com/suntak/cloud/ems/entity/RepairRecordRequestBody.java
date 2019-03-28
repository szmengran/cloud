package com.suntak.cloud.ems.entity;
/** 
 * @Package com.suntak.cloud.ems.entity 
 * @Description: 维修记录请求实体
 * @date Mar 19, 2019 2:05:33 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class RepairRecordRequestBody {
    
    private Ems_dm_repair_record repairRecord;
    private Ems_dm_part_replace[] partReplaces;
    public Ems_dm_repair_record getRepairRecord() {
        return repairRecord;
    }
    public void setRepairRecord(Ems_dm_repair_record repairRecord) {
        this.repairRecord = repairRecord;
    }
    public Ems_dm_part_replace[] getPartReplaces() {
        return partReplaces;
    }
    public void setPartReplaces(Ems_dm_part_replace[] partReplaces) {
        this.partReplaces = partReplaces;
    }
    
}
