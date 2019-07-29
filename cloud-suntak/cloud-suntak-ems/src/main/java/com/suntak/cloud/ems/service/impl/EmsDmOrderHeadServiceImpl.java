package com.suntak.cloud.ems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.ems.entity.Ems_dm_order_head;
import com.suntak.cloud.ems.mapper.EmsDmOrderHeadMapper;
import com.suntak.cloud.ems.service.EmsDmOrderHeadService;

/** 
 * @Package com.suntak.cloud.ems.service.impl 
 * @Description: TODO
 * @date Jul 23, 2019 10:05:59 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class EmsDmOrderHeadServiceImpl implements EmsDmOrderHeadService {

    @Autowired
    private EmsDmOrderHeadMapper emsDmOrderHeadMapper;
    
    @Override
    public List<Ems_dm_order_head> findOrders(Integer use_p_id) throws Exception {
        return emsDmOrderHeadMapper.findOrders(use_p_id);
    }

}
