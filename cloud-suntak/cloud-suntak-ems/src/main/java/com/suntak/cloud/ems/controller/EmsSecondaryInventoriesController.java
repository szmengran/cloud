package com.suntak.cloud.ems.controller;
/** 
 * @Package com.suntak.cloud.ems.controller 
 * @Description: TODO
 * @date Jul 23, 2019 2:53:44 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.suntak.cloud.ems.entity.Ems_secondary_inventories_v;
import com.suntak.cloud.ems.entity.ext.BaseRequest;
import com.suntak.cloud.ems.service.EmsSecondaryInventoriesService;
import com.suntak.exception.model.Response;

@RestController
@RequestMapping("/api/v1/ems")
public class EmsSecondaryInventoriesController {

    @Autowired
    private EmsSecondaryInventoriesService emsSecondaryInventoriesService;
    
    @PostMapping("/inventories")
    public Response findInventories(@RequestBody BaseRequest baseRequest) throws Exception {
        int pageNum = 1;
        int pageSize = 50;
        if (baseRequest.getPageNum() != null) {
            pageNum = baseRequest.getPageNum();
            pageSize = baseRequest.getPageSize();
        }
        PageHelper.startPage(pageNum, pageSize, "code asc");
        List<Ems_secondary_inventories_v> list = emsSecondaryInventoriesService.findInventories(baseRequest.getOrg_id());
        Response response = new Response();
        response.setData(list);
        return response;
    }
}
