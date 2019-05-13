package com.suntak.cloud.ems.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.suntak.exception.model.Response;

/** 
 * @Package com.suntak.cloud.ems.client 
 * @Description: 崇达微服务应用
 * @date Apr 30, 2019 10:17:13 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name="microservices")
public interface MicroserviceClient {
    
    @GetMapping("/api/v1/microservices/company/{companyCode}")
    public Response getOrgIdByCompanyCode(@PathVariable("companyCode") String companyCode) throws Exception;
}
