package com.suntak.cloud.ems.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suntak.cloud.ems.entity.Ems_dm_part_replace;
import com.suntak.cloud.ems.entity.Ems_dm_repair_record;
import com.suntak.cloud.ems.entity.RepairRecordRequestBody;
import com.suntak.cloud.ems.mapper.PartReplaceMapper;
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
    private PartReplaceMapper partReplaceMapper;
    
    @Transactional
    @Override
    public Boolean insert(RepairRecordRequestBody repairRecordRequestBody) throws Exception {
        Ems_dm_repair_record ems_dm_repair_record = repairRecordRequestBody.getRepairRecord();
        Ems_dm_part_replace[] ems_dm_part_replace = repairRecordRequestBody.getPartReplaces();
        partReplaceMapper.insertBatch(Arrays.asList(ems_dm_part_replace));
        return repairRecordMapper.insert(ems_dm_repair_record) == 1;
    }
    
}
