package com.suntak.cloud.ems.controller;
/** 
 * @Package com.suntak.cloud.ems.controller 
 * @Description: TODO
 * @date Aug 26, 2019 9:22:07 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.suntak.admin.user.exception.BusinessException;
import com.suntak.cloud.ems.entity.Ems_dm_maintain;
import com.suntak.cloud.ems.entity.Ems_dm_maintain_content;
import com.suntak.cloud.ems.entity.MaintainRequest;
import com.suntak.cloud.ems.entity.ext.Ems_dm_maintainExt;
import com.suntak.cloud.ems.entity.ext.Oz_org_userinfo_ext;
import com.suntak.cloud.ems.service.EmsDmMaintainService;
import com.suntak.exception.model.Response;
import com.suntak.utils.JwtUtil;

@RestController
@RequestMapping("/api/v1/ems")
public class EmsDmMaintainController {

    @Autowired
    private EmsDmMaintainService emsDmMaintainService;
    
    @PostMapping("/maintains/{token}")
    public Response findMaintain(@RequestBody Ems_dm_maintainExt maintain, @PathVariable("token") String token) throws Exception {
    	String userJson = JwtUtil.parseToken(token);
        if (userJson == null) {
            throw new BusinessException(10007001);
        }
        Oz_org_userinfo_ext userinfo = new Gson().fromJson(userJson, Oz_org_userinfo_ext.class);
    	Integer pageNum = maintain.getPageNum();
        Integer pageSize = maintain.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
            pageSize = 50;
        }
        Integer id = userinfo.getId();
        if (userinfo.getName().indexOf("管理员") != -1) {
        	id = null;
        }
        PageHelper.startPage(pageNum, pageSize, "plan_date desc");
        List<Ems_dm_maintain> list = emsDmMaintainService.findMaintain(maintain.getOrganization_id(), maintain.getKeyword(), null, id);
        Response response = new Response();
        response.setData(list);
        return response;
    }
    
    @PostMapping("/myMaintains/{token}")
    public Response findMyMaintain(@RequestBody Ems_dm_maintainExt maintain, @PathVariable("token") String token) throws Exception {
        String userJson = JwtUtil.parseToken(token);
        if (userJson == null) {
            throw new BusinessException(10007001);
        }
        Oz_org_userinfo_ext userinfo = new Gson().fromJson(userJson, Oz_org_userinfo_ext.class);
        Response response = new Response();
        if (userinfo == null) {
            response.setStatus(501);
            response.setMessage("你的企业微信账号还没有和设备系统关联，请联系设备管理人员进行关联！");
            return response;
        }
        Integer pageNum = maintain.getPageNum();
        Integer pageSize = maintain.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
            pageSize = 50;
        }
        PageHelper.startPage(pageNum, pageSize, "plan_date desc");
        List<Ems_dm_maintain> list = emsDmMaintainService.findMaintain(maintain.getOrganization_id(), maintain.getKeyword(), userinfo.getId(), null);
        response.setData(list);
        return response;
    }
    
    /**
     * 保存保养单信息并同步OA
     * @param maintainRequest
     * @param token
     * @return
     * @throws Exception
     */
    @PostMapping("/maintain/{token}")
    public Response saveOrUpdate(@RequestBody MaintainRequest maintainRequest, @PathVariable("token") String token) throws Exception {
    	Ems_dm_maintain maintain = maintainRequest.getEms_dm_maintain();
        Ems_dm_maintain_content[] maintainContent = maintainRequest.getEms_dm_maintain_content();
        String userJson = JwtUtil.parseToken(token);
        if (userJson == null) {
            throw new BusinessException(10007001);
        }
        Oz_org_userinfo_ext userinfo = new Gson().fromJson(userJson, Oz_org_userinfo_ext.class);
        Response response = new Response();
        if (userinfo == null) {
            response.setStatus(501);
            response.setMessage("你的企业微信账号还没有和设备系统关联，请联系设备管理人员进行关联！");
            return response;
        }
        maintain.setSolo_person_id(userinfo.getId());
        maintain.setSolo_person_name(userinfo.getName());
        maintain.setMaintain_person(userinfo.getName());
    	emsDmMaintainService.saveOrUpdate(userinfo.getEmployer_id(), maintain, maintainContent);
        return new Response();
    }
}
