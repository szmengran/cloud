package com.suntak.cloud.ems.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suntak.cloud.ems.entity.Ems_dm_part_replace;
import com.suntak.cloud.ems.entity.Ems_dm_repair_record;
import com.suntak.cloud.ems.entity.RepairRecordRequestBody;
import com.suntak.cloud.ems.entity.ext.Ems_dm_repair_record_ext;
import com.suntak.cloud.ems.mapper.PartReplaceMapper;
import com.suntak.cloud.ems.mapper.RepairRecordExtMapper;
import com.suntak.cloud.ems.mapper.RepairRecordMapper;
import com.suntak.cloud.ems.service.RepairRecordService;

/** 
 * @Package com.suntak.cloud.ems.service.impl 
 * @Description: 维修记录服务
 * @date Mar 18, 2019 10:43:49 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class RepairRecordServiceImpl implements RepairRecordService {

    @Autowired
    private RepairRecordMapper repairRecordMapper;
    
    @Autowired
    private RepairRecordExtMapper repairRecordExtMapper;
    
    @Autowired
    private PartReplaceMapper partReplaceMapper;
    
    @Transactional
    @Override
    public Boolean insert(RepairRecordRequestBody repairRecordRequestBody) throws Exception {
        Long id = repairRecordMapper.findSequence();
        Ems_dm_repair_record ems_dm_repair_record = repairRecordRequestBody.getRepairRecord();
        Ems_dm_part_replace[] ems_dm_part_replaces = repairRecordRequestBody.getPartReplaces();
        Long equipment_id = ems_dm_repair_record.getEquipment_id();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        for (Ems_dm_part_replace partReplace: ems_dm_part_replaces) {
            partReplace.setEquipment_id(equipment_id);
            partReplace.setRepair_id(id);
            partReplace.setPart_id(partReplace.getId());
            partReplace.setReplace_date(currentTime);
            partReplaceMapper.insert(partReplace);
        }
        Integer status = ems_dm_repair_record.getStatus();
        ems_dm_repair_record.setStatus(status== null ? 3 : status);
        ems_dm_repair_record.setMaintenance_no(getMaintenanceNo());
        ems_dm_repair_record.setId(id);
        return repairRecordMapper.insert(ems_dm_repair_record) == 1;
    }
    
    private String getMaintenanceNo() {
        String date = new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date());
        return "WX"+date;
    }

    @Override
    public List<Ems_dm_repair_record_ext> findRepairRecord(Integer userid, String keyword) throws Exception {
        return repairRecordExtMapper.findRepairRecord(userid, keyword);
    }
    
}
