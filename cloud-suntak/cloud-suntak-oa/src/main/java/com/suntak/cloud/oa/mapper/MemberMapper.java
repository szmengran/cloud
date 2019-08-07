package com.suntak.cloud.oa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.suntak.cloud.oa.entity.Org_member;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.oa.mapper 
 * @Description: OA员工信息映射表
 * @date Aug 1, 2019 1:33:15 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface MemberMapper extends IMapper<Org_member> {

    /**
     * 根据工号查询用户ID
     * @param code
     * @return
     * @throws Exception      
     * @return: List<Org_member>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @Select("select id, name, code from org_member where code=#{code}")
    List<Org_member> findByCode(@Param("code") String code) throws Exception;
}
