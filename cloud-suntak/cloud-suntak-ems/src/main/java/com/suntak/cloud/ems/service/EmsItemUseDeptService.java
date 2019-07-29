package com.suntak.cloud.ems.service;

import java.util.List;

import com.suntak.cloud.ems.entity.Ems_item_use_dept_v;

/** 
 * @Package com.suntak.cloud.ems.service 
 * @Description: TODO
 * @date Jul 23, 2019 1:57:10 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface EmsItemUseDeptService {

    /**
     * 查找部门信息
     * @param organization_id
     * @param keyword
     * @return
     * @throws Exception      
     * @return: List<Ems_item_use_dept_v>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    List<Ems_item_use_dept_v> findDeptInfo(Integer organization_id, String keyword) throws Exception;
}
