package com.suntak.cloud.ems.service;

import java.util.List;

import com.suntak.cloud.ems.entity.Ems_dm_order_head;

/** 
 * @Package com.suntak.cloud.ems.service 
 * @Description: TODO
 * @date Jul 23, 2019 10:05:28 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface EmsDmOrderHeadService {

    List<Ems_dm_order_head> findOrders(Integer use_p_id) throws Exception;
}
