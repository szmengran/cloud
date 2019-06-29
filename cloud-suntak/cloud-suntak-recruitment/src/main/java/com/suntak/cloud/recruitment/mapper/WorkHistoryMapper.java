package com.suntak.cloud.recruitment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.suntak.cloud.recruitment.entity.T_hr_workhistory;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.recruitment.mapper 
 * @Description: TODO
 * @date Jun 14, 2019 11:15:08 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface WorkHistoryMapper<T> extends IMapper<T> {
    
    @Select("select * from t_hr_workhistory where applicantid=#{applicantid}")
    List<T_hr_workhistory> findByApplicantid(String applicantid) throws Exception;
}
