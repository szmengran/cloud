package com.suntak.cloud.ems.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suntak.cloud.ems.entity.Ems_dm_order_head;
import com.suntak.cloud.ems.entity.Ems_dm_order_line;
import com.suntak.cloud.ems.mapper.EmsDmOrderHeadMapper;
import com.suntak.cloud.ems.mapper.EmsDmOrderLineMapper;
import com.suntak.cloud.ems.service.EmsDmOrderHeadService;

/** 
 * @Package com.suntak.cloud.ems.service.impl 
 * @Description: 配件领用头服务
 * @date Jul 23, 2019 10:05:59 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class EmsDmOrderHeadServiceImpl implements EmsDmOrderHeadService {

    @Autowired
    private EmsDmOrderHeadMapper emsDmOrderHeadMapper;
    
    @Autowired
    private EmsDmOrderLineMapper emsDmOrderLineMapper;
    
    @Override
    public List<Ems_dm_order_head> findOrders(Integer use_p_id) throws Exception {
        return emsDmOrderHeadMapper.findOrders(use_p_id);
    }

    @Transactional
    @Override
    public Long insert(Ems_dm_order_head ems_dm_order_head, Ems_dm_order_line[] lines) throws Exception {
        Long id = emsDmOrderHeadMapper.findSeq();
        ems_dm_order_head.setId(id);
        ems_dm_order_head.setDate_time(new Timestamp(System.currentTimeMillis()));
        emsDmOrderHeadMapper.insert(ems_dm_order_head);
        for (Ems_dm_order_line order_line: lines) {
            order_line.setHead_id(id);
            emsDmOrderLineMapper.insert(order_line);
        }
        return id;
    }

    
}
