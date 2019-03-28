package com.suntak.cloud.ems.service;

import com.suntak.cloud.ems.entity.Oz_org_userinfo;

/** 
 * @Package com.suntak.cloud.ems.service 
 * @Description: 人员操作服务
 * @date Mar 26, 2019 3:21:34 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface UserinfoService {
    
    /**
     * 根据员工工号查询用户信息
     * @param employerId
     * @return
     * @throws Exception      
     * @return: Oz_org_userinfo      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    Oz_org_userinfo findUserByEmployerId(String employerId) throws Exception;
}
