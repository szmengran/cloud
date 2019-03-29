package com.suntak.cloud.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.suntak.admin.user.exception.BusinessException;
import com.suntak.cloud.oa.service.OaService;
import com.suntak.ehr.entity.EhrUser;
import com.suntak.exception.model.Response;
import com.suntak.utils.JwtUtil;

import io.swagger.annotations.Api;

/** 
 * @Package com.suntak.cloud.oa.controller 
 * @Description: 员工学历提升计划API
 * @date Mar 28, 2019 4:27:49 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "员工学历提升计划API")
@RestController
@RequestMapping(path="/api/v1/oa", produces = { "application/json" })
public class PersonalDevPlanController {

    @Autowired
    @Qualifier("personalDevPlanService")
    private OaService personalDevPlanService;
    
    @PatchMapping("sign/plan/{id}/{token}")
    public Response signById(@PathVariable("id") String id, @PathVariable("token") String token) throws Exception {
        String userJson = JwtUtil.parseToken(token);
        if (userJson == null) {
            throw new BusinessException(10007001);
        }
        EhrUser ehrUser = new Gson().fromJson(userJson, EhrUser.class);
        personalDevPlanService.signById(id, ehrUser.getEmpcode(), ehrUser.getEmpname());
        return new Response();
    }
    
    @GetMapping("plan/{id}")
    public Response findById(@PathVariable("id") String id) throws Exception {
        Object obj = personalDevPlanService.findById(id);
        Response response = new Response();
        response.setData(obj);
        return response;
    }
}
