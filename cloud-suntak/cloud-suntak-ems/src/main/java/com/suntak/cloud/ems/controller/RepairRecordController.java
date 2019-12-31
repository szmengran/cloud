package com.suntak.cloud.ems.controller;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.suntak.admin.user.exception.BusinessException;
import com.suntak.cloud.ems.entity.Ems_dm_repair_record;
import com.suntak.cloud.ems.entity.RepairRecordRequestBody;
import com.suntak.cloud.ems.entity.ext.Ems_dm_repair_record_ext;
import com.suntak.cloud.ems.entity.ext.Oz_org_userinfo_ext;
import com.suntak.cloud.ems.entity.ext.RepairRequest;
import com.suntak.cloud.ems.service.RepairRecordService;
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
public class RepairRecordController {
    
    private final static Logger logger = LoggerFactory.getLogger(RepairRecordController.class);
    
    @Autowired
    private RepairRecordService repairRecordService;
    
    @ApiOperation("维修记录保存")
    @PostMapping("repairRecord/{token}")
    public Response insert(@PathVariable("token") String token, @RequestBody RepairRecordRequestBody repairRecordRequestBody) throws Exception {
        logger.info("维修记录保存请求：{}，{}", token, new Gson().toJson(repairRecordRequestBody));
        String userJson = JwtUtil.parseToken(token);
        if (userJson == null) {
            throw new BusinessException(10007001);
        }
        Oz_org_userinfo_ext userinfo = new Gson().fromJson(userJson, Oz_org_userinfo_ext.class);
        Response response = new Response();
        if (userinfo == null) {
            response.setStatus(501);
            response.setMessage("你的企业微信账号还没有和设备系统关联，请联系设备管理人员进行关联！");
        }
        Ems_dm_repair_record ems_dm_repair_record = repairRecordRequestBody.getRepairRecord();
        Integer userid = userinfo.getId();
        String name = userinfo.getName();
        ems_dm_repair_record.setMaintenance_apllicant_id(userid);
        ems_dm_repair_record.setMaintenance_apllicant(name);
        ems_dm_repair_record.setMaintenance_person_id(userid);
        ems_dm_repair_record.setMaintenance_person(name);
        ems_dm_repair_record.setDistribution_id(userid);
        ems_dm_repair_record.setDistribution_by(name);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        ems_dm_repair_record.setDistribution_date(currentTime);;
        ems_dm_repair_record.setCreated_date(currentTime);
        ems_dm_repair_record.setUpdated_date(currentTime);
        String employerId = userinfo.getEmployer_id();
    	ems_dm_repair_record.setCreated_by(employerId);
    	ems_dm_repair_record.setUpdated_by(employerId);
        ems_dm_repair_record.setOrganization_id(userinfo.getOrg_id());
        Boolean flag = repairRecordService.insert(repairRecordRequestBody);
        if (!flag) {
            response.setStatus(500);
            response.setMessage("保存维修记录失败！");
        }
        return response;
    }
    
    /**
     * 
     * @description 生产维修申请
     * @param token
     * @param repairRecordRequestBody
     * @return
     * @throws Exception
     * @date Nov 12, 2019 11:23:55 AM
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @ApiOperation("生产维修申请")
    @PostMapping("repairRecord/prod/{token}")
    public Response insertProd(@PathVariable("token") String token, @RequestBody RepairRecordRequestBody repairRecordRequestBody) throws Exception {
    	logger.info("{}", new Gson().toJson(token));
    	String userJson = JwtUtil.parseToken(token);
    	if (userJson == null) {
    		throw new BusinessException(10007001);
    	}
    	Oz_org_userinfo_ext userinfo = new Gson().fromJson(userJson, Oz_org_userinfo_ext.class);
    	Response response = new Response();
    	if (userinfo == null) {
    		response.setStatus(501);
    		response.setMessage("你的企业微信账号还没有和设备系统关联，请联系设备管理人员进行关联！");
    	}
    	Ems_dm_repair_record ems_dm_repair_record = repairRecordRequestBody.getRepairRecord();
    	String employerId = userinfo.getEmployer_id();
    	String name = userinfo.getName();
    	ems_dm_repair_record.setMaintenance_apllicant(name);
    	Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    	ems_dm_repair_record.setDistribution_date(currentTime);;
    	ems_dm_repair_record.setCreated_date(currentTime);
    	ems_dm_repair_record.setUpdated_date(currentTime);
    	ems_dm_repair_record.setCreated_by(employerId);
    	ems_dm_repair_record.setUpdated_by(employerId);
    	Boolean flag = repairRecordService.insert(repairRecordRequestBody);
    	if (!flag) {
    		response.setStatus(500);
    		response.setMessage("维修申请失败！");
    	}
    	return response;
    }
    
    @PostMapping("repairRecords/{token}")
    public Response findRepairRecord(@PathVariable("token") String token, @RequestBody RepairRequest repairRequest) throws Exception {
        logger.info("{}", new Gson().toJson(token));
        String userJson = JwtUtil.parseToken(token);
        if (userJson == null) {
            throw new BusinessException(10007001);
        }
        Oz_org_userinfo_ext userinfo = new Gson().fromJson(userJson, Oz_org_userinfo_ext.class);
        if (userinfo == null) {
            Response response = new Response();
            response.setStatus(501);
            response.setMessage("你的企业微信账号还没有和设备系统关联，请联系设备管理人员进行关联！");
            return response;
        }
        Integer pageNum = repairRequest.getPageNum();
        Integer pageSize = repairRequest.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize, "a.maintenance_time desc");
        Integer userid = userinfo.getId();
        
        List<Ems_dm_repair_record_ext> list = repairRecordService.findRepairRecord(userid, repairRequest.getKeyword());
        Response response = new Response();
        response.setData(list);
        return response;
    }
    
    /**
     * 
     * @description 查找用户的生产维修申报记录
     * @param token
     * @param repairRequest
     * @return
     * @throws Exception
     * @date Nov 13, 2019 1:41:16 PM
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @PostMapping("repairRecords/prod/{token}")
    public Response findRepairRecordProd(@PathVariable("token") String token, @RequestBody RepairRequest repairRequest) throws Exception {
    	logger.info("{}", new Gson().toJson(token));
    	String userJson = JwtUtil.parseToken(token);
    	if (userJson == null) {
    		throw new BusinessException(10007001);
    	}
    	Oz_org_userinfo_ext userinfo = new Gson().fromJson(userJson, Oz_org_userinfo_ext.class);
    	if (userinfo == null) {
    		Response response = new Response();
    		response.setStatus(501);
    		response.setMessage("你的企业微信账号不存在！");
    		return response;
    	}
    	Integer pageNum = repairRequest.getPageNum();
    	Integer pageSize = repairRequest.getPageSize();
    	if (pageNum == null) {
    		pageNum = 1;
    		pageSize = 10;
    	}
    	PageHelper.startPage(pageNum, pageSize, "a.maintenance_time desc");
    	String empcode = userinfo.getEmployer_id();
    	List<Ems_dm_repair_record_ext> list = repairRecordService.findRepairRecordProd(empcode, repairRequest.getKeyword());
    	Response response = new Response();
    	response.setData(list);
    	return response;
    }
}
