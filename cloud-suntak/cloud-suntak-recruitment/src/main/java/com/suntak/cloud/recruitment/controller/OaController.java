package com.suntak.cloud.recruitment.controller;
/** 
 * @Package com.suntak.cloud.recruitment.controller 
 * @Description: OA对接
 * @date Jun 14, 2019 10:07:37 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.exception.model.Response;

@RestController
@RequestMapping(path = "/api/v1/recruitment", produces = { "application/json" })
public class OaController {

    @GetMapping("/oa/{applicantid}")
    public Response launchForm(@PathVariable("applicantid") String applicantid) throws Exception {
        return new Response();
    }
}
