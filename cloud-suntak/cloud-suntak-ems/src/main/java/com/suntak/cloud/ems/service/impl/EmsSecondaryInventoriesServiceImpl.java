package com.suntak.cloud.ems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.ems.entity.Ems_secondary_inventories_v;
import com.suntak.cloud.ems.mapper.EmsSecondaryInventoriesMapper;
import com.suntak.cloud.ems.service.EmsSecondaryInventoriesService;

/** 
 * @Package com.suntak.cloud.ems.service.impl 
 * @Description: 来源库查找服务
 * @date Jul 23, 2019 2:49:00 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class EmsSecondaryInventoriesServiceImpl implements EmsSecondaryInventoriesService {

    @Autowired
    private EmsSecondaryInventoriesMapper emsSecondaryInventoriesMapper;
    
    @Override
    public List<Ems_secondary_inventories_v> findInventories(Integer organization_id) throws Exception {
        return emsSecondaryInventoriesMapper.findInventories(organization_id);
    }

}
