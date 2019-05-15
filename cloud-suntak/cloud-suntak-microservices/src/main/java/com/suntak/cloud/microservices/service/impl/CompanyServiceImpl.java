package com.suntak.cloud.microservices.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.microservices.entity.T_report_company_r_org;
import com.suntak.cloud.microservices.mapper.CompanyMapper;
import com.suntak.cloud.microservices.service.CompanyService;

/** 
 * @Package com.suntak.cloud.microservices.service.impl 
 * @Description: 部门信息服务
 * @date Apr 30, 2019 10:03:18 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    
    @Override
    public Integer findByCompanyCode(String companyCode) {
        List<T_report_company_r_org> list = companyMapper.findByCompanyCode(companyCode);
        if (list != null && list.size() > 0) {
            return list.get(0).getOrg_id();
        }
        return null;
    }

}
