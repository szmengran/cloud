package com.suntak.cloud.ems.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.exception.model.Response;

/** 
 * @Package com.suntak.cloud.ems.controller 
 * @Description: 维修记录API
 * @date Mar 15, 2019 2:50:27 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(path="/api/v1/ems", produces = { "application/json" })
public class RepairRecordController {
    
    @GetMapping("hello")
    public Response sayHello() throws Exception {
        return new Response();
    }
}
