package com.suntak.cloud.microservices.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.suntak.cloud.microservices.entity.T_report_company_r_org;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.microservices.mapper 
 * @Description: 部门信息数据库操作
 * @date Apr 30, 2019 9:49:39 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface CompanyMapper extends IMapper<T_report_company_r_org> {
    
    @Select("select * from t_report_company_r_org where companycode=#{companyCode}")
    List<T_report_company_r_org> findByCompanyCode(String companyCode);
}
