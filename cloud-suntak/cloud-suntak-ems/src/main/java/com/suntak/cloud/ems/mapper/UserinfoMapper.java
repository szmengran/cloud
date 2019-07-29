package com.suntak.cloud.ems.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.suntak.cloud.ems.entity.Oz_org_userinfo;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.ems.mapper 
 * @Description: 人员信息数据库操作
 * @date Mar 26, 2019 3:11:31 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface UserinfoMapper extends IMapper<Oz_org_userinfo> {

    /**
     * 根据用户工号查询设备系统中的用户ID和用户名称
     * @param employerId
     * @return
     * @throws Exception      
     * @return: List<Oz_org_userinfo>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @Select("select id, name, mobile, employer_id from oz_org_userinfo where employer_id=#{employerId} order by id")
    List<Oz_org_userinfo> findUserinfo(@Param("employerId") String employerId) throws Exception;
}
