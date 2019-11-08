package com.suntak.reserve.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.suntak.exception.model.Response;

/** 
 * @Package com.suntak.cloud.recruitment.client 
 * @Description: TODO
 * @date Jul 8, 2019 4:49:53 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "cloud-suntak-oa")
public interface OaClient {

    /**
     * 根据工号查找OA用户的信息
     * @param code
     * @return
     * @throws Exception      
     * @return: Response      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @GetMapping(path = "/api/v1/oa/user/{code}")
    Response findByCode(@PathVariable("code") String code) throws Exception;
    
    /**
     * 根据工号查询用户信息
     * @param code
     * @return
     * @throws Exception      
     * @return: Response      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @GetMapping(path = "/api/v1/oa/member/{code}")
    Response findMemberByCode(@PathVariable("code") String code) throws Exception;
}
