package com.suntak.cloud.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.oa.entity.Org_member;
import com.suntak.cloud.oa.service.MemberService;
import com.suntak.exception.model.Response;

/** 
 * @Package com.suntak.cloud.oa.controller 
 * @Description: TODO
 * @date Aug 1, 2019 1:41:22 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping("/api/v1/oa")
public class MemberController {

    @Autowired
    private MemberService memberService;
    
    @GetMapping("/member/{code}")
    public Response findByCode(@PathVariable("code") String code) throws Exception {
        Org_member member = memberService.findByCode(code);
        Response response = new Response();
        response.setData(member);
        return response;
    }
}
