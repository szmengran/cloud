package com.suntak.cloud.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.ems.service.UserinfoService;
import com.suntak.exception.model.Response;

/** 
 * @Package com.suntak.cloud.ems.controller 
 * @Description: 人员服务
 * @date Mar 26, 2019 3:28:52 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping("/api/v1/ems")
public class UserinfoController {

    @Autowired
    private UserinfoService userinfoService;
    
    @GetMapping("/userinfo/{employerId}")
    public Response findUserinfo(@PathVariable("employerId") String employerId) throws Exception {
        Response response = new Response();
        response.setData(userinfoService.findUserByEmployerId(employerId));
        return response;
    }
}
