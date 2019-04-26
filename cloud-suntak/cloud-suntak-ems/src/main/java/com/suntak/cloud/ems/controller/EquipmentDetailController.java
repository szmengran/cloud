package com.suntak.cloud.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.suntak.cloud.ems.entity.Ems_dm_equipment_details;
import com.suntak.cloud.ems.entity.ext.EquipmentDetailRequest;
import com.suntak.cloud.ems.service.EquipmentDetailService;
import com.suntak.exception.model.Response;

/** 
 * @Package com.suntak.cloud.ems.controller 
 * @Description: 设备明细服务
 * @date Mar 26, 2019 5:18:47 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping("/api/v1/ems")
public class EquipmentDetailController {

    @Autowired
    private EquipmentDetailService equipmentDetailService;
    
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
                equipmentDetailRequest.getProcedure(), equipmentDetailRequest.getKeyword());
        Response response = new Response();
        response.setData(list);
        return response;
    }
}
