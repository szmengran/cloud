package com.suntak.cloud.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.suntak.admin.user.exception.BusinessException;
import com.suntak.cloud.ems.entity.Ems_dm_order_head;
import com.suntak.cloud.ems.entity.ext.OrderRequest;
import com.suntak.cloud.ems.entity.ext.Oz_org_userinfo_ext;
import com.suntak.cloud.ems.service.EmsDmOrderHeadService;
import com.suntak.exception.model.Response;
import com.suntak.utils.JwtUtil;

/** 
 * @Package com.suntak.cloud.ems.controller 
 * @Description: TODO
 * @date Jul 23, 2019 8:19:54 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
public class EmsDmOrderHeadController {

    @Autowired
    private EmsDmOrderHeadService emsDmOrderHeadService;
    
    @GetMapping("/orders/{token}/{pageNum}/{pageSize}")
    public Response findOrders(@PathVariable("token") String token, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) throws Exception {
        String userJson = JwtUtil.parseToken(token);
        if (userJson == null) {
            throw new BusinessException(10007001);
        }
        Oz_org_userinfo_ext userinfo = new Gson().fromJson(userJson, Oz_org_userinfo_ext.class);
        PageHelper.startPage(pageNum, pageSize, "date_time desc");
        List<Ems_dm_order_head> list = emsDmOrderHeadService.findOrders(userinfo.getId());
        Response response = new Response();
        response.setData(list);
        return response;
    }
    
    @PostMapping("/order/{token}")
    public Response insert(@PathVariable("token") String token, @RequestBody OrderRequest request) throws Exception {
    	String userJson = JwtUtil.parseToken(token);
        if (userJson == null) {
            throw new BusinessException(10007001);
        }
        Oz_org_userinfo_ext userinfo = new Gson().fromJson(userJson, Oz_org_userinfo_ext.class);
        Long id = emsDmOrderHeadService.insert(userinfo.getEmployer_id(), request.getOrder_head(), request.getOrder_lines());
        Response response = new Response();
        response.setData(id);
        return response;
    }
    
    @PostMapping("/submitebs/{org_id}/{id}")
    public Response submitEbs(Integer org_id, Long id) {
    	emsDmOrderHeadService.submitEbs(org_id, id);
    	return new Response();
    }
}
