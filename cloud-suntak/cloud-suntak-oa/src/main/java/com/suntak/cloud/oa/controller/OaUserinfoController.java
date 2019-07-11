package com.suntak.cloud.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.oa.entity.Oa_user_info;
import com.suntak.cloud.oa.service.OaUserinfoService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;

/** 
 * @Package com.suntak.cloud.oa.controller 
 * @Description: OA用户信息查询
 * @date Jul 8, 2019 4:43:41 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "OA用户信息查询")
@RestController
@RequestMapping(path = "/api/v1/oa")
public class OaUserinfoController {

    @Autowired
    private OaUserinfoService oaUserinfoService;
    
    @GetMapping("/user/{code}")
    public Response findByCode(@PathVariable("code") String code) throws Exception {
        Oa_user_info userinfo = oaUserinfoService.findByCode(code);
        Response response = new Response();
        response.setData(userinfo);
        return response;
    }
}
