package com.suntak.cloud.ems.service;

import java.util.List;

import com.suntak.cloud.ems.entity.Ems_dm_order_head;
import com.suntak.cloud.ems.entity.Ems_dm_order_line;

/** 
 * @Package com.suntak.cloud.ems.service 
 * @Description: 配件出库主表信息
 * @date Jul 23, 2019 10:05:28 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface EmsDmOrderHeadService {

    /**
     * 查找配件出库信息
     * @param use_p_id
     * @return
     * @throws Exception      
     * @return: List<Ems_dm_order_head>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    List<Ems_dm_order_head> findOrders(Integer use_p_id) throws Exception;
    
    /**
     * 插入信息
     * @param ems_dm_order_head
     * @param line
     * @return
     * @throws Exception      
     * @return: Boolean      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    Long insert(Ems_dm_order_head ems_dm_order_head, Ems_dm_order_line[] line) throws Exception;
}
