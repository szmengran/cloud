package com.suntak.cloud.ems.service;

import java.util.List;

import com.suntak.cloud.ems.entity.Ems_user_v;

/** 
 * @Package com.suntak.cloud.ems.service 
 * @Description: EBS用户查找服务
 * @date Jul 30, 2019 2:39:54 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface EmsUserVService {

    /**
     * EBS用户查询
     * @param user_name
     * @return
     * @throws Exception      
     * @return: Ems_user_v      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    Ems_user_v findByUsername(String user_name) throws Exception;
    
    /**
     * 根据关键字查找用户
     * @param keyword
     * @return
     * @throws Exception      
     * @return: List<Ems_user_v>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    List<Ems_user_v> findUser(String keyword) throws Exception;
}
