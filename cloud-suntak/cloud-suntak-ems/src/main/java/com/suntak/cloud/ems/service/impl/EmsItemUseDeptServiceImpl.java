package com.suntak.cloud.ems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.ems.entity.Ems_item_use_dept_v;
import com.suntak.cloud.ems.mapper.EmsItemUseDeptMapper;
import com.suntak.cloud.ems.service.EmsItemUseDeptService;

/** 
 * @Package com.suntak.cloud.ems.service.impl 
 * @Description: TODO
 * @date Jul 23, 2019 1:58:25 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class EmsItemUseDeptServiceImpl implements EmsItemUseDeptService {

    @Autowired
    private EmsItemUseDeptMapper emsItemUseDeptMapper;
    
    @Override
    public List<Ems_item_use_dept_v> findDeptInfo(Integer organization_id, String keyword) throws Exception {
        return emsItemUseDeptMapper.findDeptInfo(organization_id, keyword);
    }

}
