package com.suntak.cloud.ems.controller;
/** 
 * @Package com.suntak.cloud.ems.controller 
 * @Description: TODO
 * @date Aug 26, 2019 9:22:07 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.ems.entity.Ems_dm_maintain;
import com.suntak.cloud.ems.entity.ext.Ems_dm_maintainExt;
import com.suntak.cloud.ems.service.EmsDmMaintainService;
import com.suntak.exception.model.Response;

@RestController
@RequestMapping("/api/v1/ems")
public class EmsDmMaintainController {

    @Autowired
    private EmsDmMaintainService emsDmMaintainService;
    
    @PostMapping("/maintain")
    public Response findEmsDmMaintain(@RequestBody Ems_dm_maintainExt maintain) throws Exception {
        List<Ems_dm_maintain> list = emsDmMaintainService.findMaintain(maintain.getOrganization_id(), maintain.getKeyword());
        Response response = new Response();
        response.setData(list);
        return response;
    }
}
