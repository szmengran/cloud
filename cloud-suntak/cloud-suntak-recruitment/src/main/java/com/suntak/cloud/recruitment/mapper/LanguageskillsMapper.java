package com.suntak.cloud.recruitment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.suntak.cloud.recruitment.entity.T_hr_languageskills;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.recruitment.mapper 
 * @Description: 语言能力持久化
 * @date Jun 14, 2019 11:02:03 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface LanguageskillsMapper<T> extends IMapper<T> {

    @Select("select * from t_hr_languageskills where applicantid=#{applicantid}")
    List<T_hr_languageskills> findByApplicantid(String applicantid) throws Exception;
}
