package com.suntak.cloud.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.suntak.cloud.ems.entity.Ems_item_use_dept_v;
import com.suntak.cloud.ems.entity.ext.BaseRequest;
import com.suntak.cloud.ems.service.EmsItemUseDeptService;
import com.suntak.exception.model.Response;

/** 
 * @Package com.suntak.cloud.ems.controller 
 * @Description: TODO
 * @date Jul 23, 2019 2:01:46 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping("/api/v1/ems")
public class EmsItemUseDeptController {
    
    @Autowired
    private EmsItemUseDeptService emsItemUseDeptService;
    
    @PostMapping("/dept")
    public Response findDeptInfo(@RequestBody BaseRequest baseRequest) throws Exception {
        int pageNum = 1;
        int pageSize = 30;
        if (baseRequest.getPageNum() != null) {
            pageNum = baseRequest.getPageNum();
            pageSize = baseRequest.getPageSize();
        }
        PageHelper.startPage(pageNum, pageSize, "disposition_id asc");
        List<Ems_item_use_dept_v> list = emsItemUseDeptService.findDeptInfo(baseRequest.getOrg_id(), baseRequest.getKeyword());
        Response response = new Response();
        response.setData(list);
        return response;
    }
}
