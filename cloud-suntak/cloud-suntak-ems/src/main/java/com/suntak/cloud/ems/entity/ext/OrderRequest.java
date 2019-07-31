package com.suntak.cloud.ems.entity.ext;

import com.suntak.cloud.ems.entity.Ems_dm_order_head;
import com.suntak.cloud.ems.entity.Ems_dm_order_line;

/** 
 * @Package com.suntak.cloud.ems.entity.ext 
 * @Description: 订单信息
 * @date Jul 31, 2019 4:16:18 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class OrderRequest {
    private Ems_dm_order_head order_head;
    private Ems_dm_order_line[] order_lines;
    public Ems_dm_order_head getOrder_head() {
        return order_head;
    }
    public void setOrder_head(Ems_dm_order_head order_head) {
        this.order_head = order_head;
    }
    public Ems_dm_order_line[] getOrder_lines() {
        return order_lines;
    }
    public void setOrder_lines(Ems_dm_order_line[] order_lines) {
        this.order_lines = order_lines;
    }
    
}
