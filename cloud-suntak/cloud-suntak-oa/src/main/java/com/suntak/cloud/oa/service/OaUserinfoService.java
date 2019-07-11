package com.suntak.cloud.oa.service;

import org.apache.ibatis.annotations.Param;

import com.suntak.cloud.oa.entity.Oa_user_info;

/** 
 * @Package com.suntak.cloud.oa.service 
 * @Description: OA用户信息
 * @date Jul 8, 2019 4:36:57 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface OaUserinfoService {

    /**
     * 根据工号查找用户
     * @param code
     * @return
     * @throws Exception      
     * @return: Oa_user_info      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    Oa_user_info findByCode(@Param("code") String code) throws Exception;
}
