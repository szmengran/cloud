package com.suntak.cloud.ems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.ems.entity.Ems_dm_repair_term_line;
import com.suntak.cloud.ems.mapper.EmsDmRepairTermLineMapper;
import com.suntak.cloud.ems.service.EmsDmRepairTermLineService;

/** 
 * @Package com.suntak.cloud.ems.service.impl 
 * @Description: TODO
 * @date Aug 26, 2019 8:41:43 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class EmsDmRepairTermLineServiceImpl implements EmsDmRepairTermLineService {

    @Autowired
    private EmsDmRepairTermLineMapper emsDmRepairTermLineMapper;
    
    @Override
    public List<Ems_dm_repair_term_line> findRepairTermLine(String equipment_name, String maintenance_level)
            throws Exception {
        return emsDmRepairTermLineMapper.findRepairTermLine(equipment_name, maintenance_level);
    }

}
