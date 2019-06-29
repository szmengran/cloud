package com.suntak.cloud.recruitment.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.suntak.cloud.recruitment.entity.T_hr_applicant;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.recruitment.mapper 
 * @Description: 应聘人员基本信息持久化操作
 * @date Jun 14, 2019 10:19:21 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface ApplicantMapper extends IMapper<T_hr_applicant> {
    
    /**
     * 更新基本资料
     * @param t_hr_applicant
     * @return
     * @throws Exception 
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @Update("update t_hr_applicant set name=#{name},sex=#{sex},birthday=#{birthday},address=#{address}" + 
            " ,phone=#{phone},email=#{email}" + 
            " ,mandarin=#{mandarin},english=#{english},japanese=#{japanese},other=#{other}" + 
            " ,relativesname=#{relativesname},relativesdepartment=#{relativesdepartment},relativesposition=#{relativesposition},relativesrelationship=#{relativesrelationship}" +
            " ,medicalhistory=#{medicalhistory},crimehistory=#{crimehistory},pregnancy=#{pregnancy},medicalhistorydesc=#{medicalhistorydesc},updatestamp=#{updatestamp}" +
            "  where applicantid=#{applicantid}")
    public int updateBaseInfo(T_hr_applicant t_hr_applicant) throws Exception;
    
}
