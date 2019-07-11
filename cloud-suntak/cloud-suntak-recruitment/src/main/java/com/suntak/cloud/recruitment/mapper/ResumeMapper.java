package com.suntak.cloud.recruitment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.suntak.cloud.recruitment.entity.T_hr_resume;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.recruitment.mapper 
 * @Description: 用户简历信息
 * @date Jul 10, 2019 10:31:12 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface ResumeMapper extends IMapper<T_hr_resume> {

    /**
     * 查找用户简历
     * @param applicantid
     * @return
     * @throws Exception      
     * @return: List<T_hr_resume>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @Select("select * from t_hr_resume where applicantid=#{applicantid} order by createstamp")
    List<T_hr_resume> findByApplicantid(@Param("applicantid") String applicantid) throws Exception;
}
