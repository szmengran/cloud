package com.suntak.cloud.microservices.service;
/** 
 * @Package com.suntak.cloud.microservices.service 
 * @Description: 部门信息服务
 * @date Apr 30, 2019 10:02:23 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface CompanyService {
    
    Integer findByCompanyCode(String companyCode);
}
