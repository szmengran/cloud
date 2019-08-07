package com.suntak.cloud.oa.service;

import org.apache.ibatis.annotations.Param;

import com.suntak.cloud.oa.entity.Org_member;

/** 
 * @Package com.suntak.cloud.oa.service 
 * @Description: TODO
 * @date Aug 1, 2019 1:36:04 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface MemberService {

    /**
     * 根据工号查找员工信息
     * @param code
     * @return
     * @throws Exception      
     * @return: Org_member      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    Org_member findByCode(@Param("code") String code) throws Exception;
}
