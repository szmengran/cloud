package com.suntak.cloud.ems.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.suntak.admin.user.exception.BusinessException;
import com.suntak.cloud.ems.entity.Ems_dm_repair_record;
import com.suntak.cloud.ems.entity.Oz_org_userinfo;
import com.suntak.cloud.ems.entity.RepairRecordRequestBody;
import com.suntak.cloud.ems.service.RepairRecordService;
import com.suntak.cloud.ems.service.UserinfoService;
import com.suntak.ehr.entity.EhrUser;
import com.suntak.exception.model.Response;
import com.suntak.utils.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 
 * @Package com.suntak.cloud.ems.controller 
 * @Description: 维修记录API
 * @date Mar 15, 2019 2:50:27 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api("维修记录API")
@RestController
@RequestMapping(path="/api/v1/ems", produces = { "application/json" })
public class RepairRecordController {
    
    private final static Logger logger = LoggerFactory.getLogger(RepairRecordController.class);
    
    @Autowired
    private RepairRecordService repairRecordService;
    
    @Autowired
    private UserinfoService userinfoService;
    
    @ApiOperation("维修记录保存")
    @PostMapping("repairRecord/{token}")
    public Response insert(@PathVariable("token") String token, @RequestBody RepairRecordRequestBody repairRecordRequestBody) throws Exception {
        logger.info("{}", new Gson().toJson(token));
        String userJson = JwtUtil.parseToken(token);
        if (userJson == null) {
            throw new BusinessException(10007001);
        }
        EhrUser ehrUser = new Gson().fromJson(userJson, EhrUser.class);
        Oz_org_userinfo userinfo = userinfoService.findUserByEmployerId(ehrUser.getEmpcode());
        Ems_dm_repair_record ems_dm_repair_record = repairRecordRequestBody.getRepairRecord();
        Integer userid = userinfo.getId();
        String name = userinfo.getName();
        ems_dm_repair_record.setMaintenance_apllicant_id(userid);
        ems_dm_repair_record.setMaintenance_apllicant(name);
        ems_dm_repair_record.setMaintenance_person_id(userid);
        ems_dm_repair_record.setMaintenance_person(name);
        Boolean flag = repairRecordService.insert(repairRecordRequestBody);
        Response response = new Response();
        if (!flag) {
            response.setStatus(500);
            response.setMessage("保存维修记录失败！");
        }
        return response;
    }
    
}
