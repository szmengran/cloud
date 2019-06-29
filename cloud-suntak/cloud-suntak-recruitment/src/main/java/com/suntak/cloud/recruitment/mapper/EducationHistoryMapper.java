package com.suntak.cloud.recruitment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.suntak.cloud.recruitment.entity.T_hr_educationhistory;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.recruitment.mapper 
 * @Description: TODO
 * @date Jun 14, 2019 10:47:58 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface EducationHistoryMapper<T> extends IMapper<T> {

    @Select("select * from t_hr_educationhistory where applicantid=#{applicantid}")
    List<T_hr_educationhistory> findByApplicantid(@Param("applicantid") String applicantid) throws Exception;
}
