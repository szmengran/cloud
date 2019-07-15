package com.suntak.cloud.ems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.ems.entity.Ems_dm_equipment_details;
import com.suntak.cloud.ems.mapper.EquipmentDetailsMapper;
import com.suntak.cloud.ems.service.EquipmentDetailService;

/** 
 * @Package com.suntak.cloud.ems.service.impl 
 * @Description: 设备明细服务
 * @date Mar 26, 2019 5:16:49 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class EquipmentDetailServiceImpl implements EquipmentDetailService {

    @Autowired
    private EquipmentDetailsMapper EquipmentDetailsMapper;
    
    @Override
    public List<Ems_dm_equipment_details> findEquipmentDetail(String useD, String procedure, String keyword, Integer org_id)
            throws Exception {
        return EquipmentDetailsMapper.findEquipmentDetail(useD, procedure, keyword, org_id);
    }

}
