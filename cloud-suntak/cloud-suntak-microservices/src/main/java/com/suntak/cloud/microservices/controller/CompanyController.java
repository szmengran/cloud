package com.suntak.cloud.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.microservices.service.CompanyService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;

/** 
 * @Package com.suntak.cloud.microservices.controller 
 * @Description: 公司信息查询
 * @date Apr 30, 2019 10:09:35 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "company")
@RestController
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;
    
    @GetMapping("/company/{companyCode}")
    public Response getOrgIdByCompanyCode(@PathVariable("companyCode") String companyCode) throws Exception {
        Integer orgId = companyService.findByCompanyCode(companyCode);
        Response response = new Response();
        response.setData(orgId);
        return response;
    }
}
