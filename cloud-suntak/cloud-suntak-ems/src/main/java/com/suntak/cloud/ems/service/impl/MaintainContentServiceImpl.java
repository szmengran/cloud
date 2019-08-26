package com.suntak.cloud.ems.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.ems.entity.Ems_dm_maintain;
import com.suntak.cloud.ems.entity.Ems_dm_maintain_content;
import com.suntak.cloud.ems.entity.Ems_dm_repair_term_line;
import com.suntak.cloud.ems.mapper.EmsDmRepairTermLineMapper;
import com.suntak.cloud.ems.mapper.MaintainContentMapper;
import com.suntak.cloud.ems.service.MaintainContentService;

/** 
 * @Package com.suntak.cloud.ems.service.impl 
 * @Description: TODO
 * @date Aug 26, 2019 10:20:27 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class MaintainContentServiceImpl implements MaintainContentService {

    @Autowired
    private MaintainContentMapper maintainContentMapper;
    
    @Autowired
    private EmsDmRepairTermLineMapper emsDmRepairTermLineMapper;
    
    @Override
    public List<Ems_dm_maintain_content> findMaintainContent(Ems_dm_maintain maintain) throws Exception {
        List<Ems_dm_maintain_content> list = maintainContentMapper.findMaintainContent(maintain.getId());
        if (list != null && list.size() > 0) {
            return list;
        }
        list = maintainContentMapper.findMaintainContentHistory(maintain.getEquipment_id(), maintain.getMaintain_level(), maintain.getStatus());
        if (list != null && list.size() > 0) {
            return list;
        }
        List<Ems_dm_repair_term_line> lines = emsDmRepairTermLineMapper.findRepairTermLine(maintain.getEquipment_name(), maintain.getMaintain_level());
        list = new ArrayList<>();
        for (Ems_dm_repair_term_line line: lines) {
            Ems_dm_maintain_content content = new Ems_dm_maintain_content();
            BeanUtils.copyProperties(line, content);
            list.add(content);
        }
        return list;
    }

}
