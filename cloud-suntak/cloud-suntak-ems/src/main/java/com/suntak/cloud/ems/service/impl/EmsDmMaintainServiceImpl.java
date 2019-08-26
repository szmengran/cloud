package com.suntak.cloud.ems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.ems.entity.Ems_dm_maintain;
import com.suntak.cloud.ems.mapper.MaintainMapper;
import com.suntak.cloud.ems.service.EmsDmMaintainService;

/** 
 * @Package com.suntak.cloud.ems.service.impl 
 * @Description: TODO
 * @date Aug 26, 2019 9:43:33 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class EmsDmMaintainServiceImpl implements EmsDmMaintainService {

    @Autowired
    private MaintainMapper maintainMapper;
    
    @Override
    public List<Ems_dm_maintain> findMaintain(Integer organization_id, String keyword) throws Exception {
        return maintainMapper.findMaintain(organization_id, keyword);
    }

}
