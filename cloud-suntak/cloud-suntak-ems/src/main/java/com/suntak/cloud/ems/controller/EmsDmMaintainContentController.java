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
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.ems.entity.Ems_dm_maintain;
import com.suntak.cloud.ems.entity.Ems_dm_maintain_content;
import com.suntak.cloud.ems.service.MaintainContentService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="保养单")
@RestController
public class EmsDmMaintainContentController {

    @Autowired
    private MaintainContentService maintainContentService;
    
    @ApiOperation("保养单信息查找")
    @PostMapping("/maintainContent")
    public Response findEmsDmMaintainContent(@RequestBody Ems_dm_maintain maintain) throws Exception {
        List<Ems_dm_maintain_content> list = maintainContentService.findMaintainContent(maintain);
        Response response = new Response();
        response.setData(list);
        return response;
    }
}
