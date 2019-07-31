package com.suntak.cloud.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.suntak.cloud.ems.entity.Ems_user_v;
import com.suntak.cloud.ems.entity.ext.BaseRequest;
import com.suntak.cloud.ems.service.EmsUserVService;
import com.suntak.exception.model.Response;

/** 
 * @Package com.suntak.cloud.ems.controller 
 * @Description: EBS用户查询
 * @date Jul 30, 2019 2:53:07 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping("/api/v1/ems")
public class EmsUserVController {

    @Autowired
    private EmsUserVService emsUserVService;
    
    @GetMapping("/emsUser/{user_name}")
    public Response findByUsername(@PathVariable("user_name") String user_name) throws Exception {
        Ems_user_v user = emsUserVService.findByUsername(user_name.toUpperCase());
        Response response = new Response();
        response.setData(user);
        return response;
    }
    
    @PostMapping("/emsUser")
    public Response findUser(@RequestBody BaseRequest baseRequest) throws Exception {
        int pageSize = 20;
        int pageNum = 1;
        if (baseRequest.getPageNum() != null) {
            pageSize = baseRequest.getPageSize();
            pageNum = baseRequest.getPageNum();
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Ems_user_v> liser = emsUserVService.findUser(baseRequest.getKeyword());
        Response response = new Response();
        response.setData(liser);
        return response;
    }
}
