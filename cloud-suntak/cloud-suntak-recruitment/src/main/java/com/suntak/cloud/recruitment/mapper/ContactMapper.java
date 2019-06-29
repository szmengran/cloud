package com.suntak.cloud.recruitment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.suntak.cloud.recruitment.entity.T_hr_contact;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.recruitment.mapper 
 * @Description: 联系人持久化
 * @date Jun 14, 2019 10:42:05 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface ContactMapper extends IMapper<T_hr_contact> {

    @Select("select * from t_hr_contact where applicantid=#{applicantid}")
    List<T_hr_contact> findByApplicantid(@Param("applicantid") String applicantid) throws Exception;
}
