package com.suntak.cloud.ems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.ems.entity.Ems_partdetail_v;
import com.suntak.cloud.ems.mapper.PartDetailMapper;
import com.suntak.cloud.ems.service.PartDetailService;

/** 
 * @Package com.suntak.cloud.ems.service.impl 
 * @Description: 配件操作服务
 * @date Mar 25, 2019 3:25:30 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class PartDetailServiceImpl implements PartDetailService {

    @Autowired
    private PartDetailMapper partDetailMapper;
    
    @Override
    public List<Ems_partdetail_v> findPartInfo(String keyword, Integer org_id) throws Exception {
        return partDetailMapper.findPartDetailInfo(keyword, org_id);
    }

    @Override
    public List<Ems_partdetail_v> findPartByNo(String part_no, Integer org_id) throws Exception {
        List<Ems_partdetail_v> list = partDetailMapper.findPartByNo(part_no, org_id);
        if (list == null || list.size() == 0) {
        	return partDetailMapper.findPartDetailInfo(part_no, org_id);
        }
        return list;
    }

}
