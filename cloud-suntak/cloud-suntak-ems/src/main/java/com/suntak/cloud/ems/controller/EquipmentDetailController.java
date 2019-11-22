package com.suntak.cloud.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.suntak.cloud.ems.entity.Ems_dm_equipment_details;
import com.suntak.cloud.ems.entity.ext.EquipmentDetailRequest;
import com.suntak.cloud.ems.service.EquipmentDetailService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 
 * @Package com.suntak.cloud.ems.controller 
 * @Description: 设备明细服务
 * @date Mar 26, 2019 5:18:47 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "设备明细服务")
@RestController
public class EquipmentDetailController {

    @Autowired
    private EquipmentDetailService equipmentDetailService;
    
    @ApiOperation("查找设备明细信息")
    @PostMapping("/equipment")
    public Response findEquipmentDetail(@RequestBody EquipmentDetailRequest equipmentDetailRequest) throws Exception {
        Integer pageNum = equipmentDetailRequest.getPageNum();
        Integer pageSize = equipmentDetailRequest.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
            pageSize = 10;
        }
        pageSize = pageSize > 100 ? 100 : pageSize;
        PageHelper.startPage(pageNum, pageSize, "equipment_name");
        List<Ems_dm_equipment_details> list = equipmentDetailService.findEquipmentDetail(equipmentDetailRequest.getUseD(), 
                equipmentDetailRequest.getProcedure(), equipmentDetailRequest.getKeyword(), equipmentDetailRequest.getOrg_id());
        Response response = new Response();
        response.setData(list);
        return response;
    }
}
