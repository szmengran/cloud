package com.suntak.cloud.recruitment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.suntak.exception.model.Response;

/** 
 * @Package com.suntak.cloud.recruitment.client 
 * @Description: EHR对接
 * @date Jun 25, 2019 4:41:04 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "cloud-suntak-ehr")
public interface EhrClient {

    @GetMapping(value = "/api/v1/ehr/users/{empcode}")
    public Response getEhrUser(@PathVariable("empcode") String empcode) throws Exception;
}
