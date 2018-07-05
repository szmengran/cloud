package com.suntak.cloud.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.test.entity.T_oa_test_create_record;
import com.suntak.cloud.test.service.CreateRecordService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Package com.suntak.cloud.test.service
 * @Description: 创建记录
 * @date 2018年6月25日 下午2:07:51
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "t_oa_test_create_record")
@RestController
@RequestMapping(value="/api/v1/test", produces = { "application/json" })
public class CreateRecordController {
	
	@Autowired
	CreateRecordService createRecordService;
	
	@ApiOperation(value = "开始制作资料", response = Response.class)
	@PostMapping(value="/t_oa_test_create_records")
	public Response insert(@RequestBody T_oa_test_create_record t_oa_test_create_record) throws Exception {
		createRecordService.insert(t_oa_test_create_record);
		return new Response();
	}
	
	@ApiOperation(value="资料制作取消", response = Response.class)
	@DeleteMapping(value="/t_oa_test_create_records/{empcode}/{create_record_id}")
	public Response cancel(@PathVariable Integer create_record_id, @PathVariable String empcode) throws Exception {
		createRecordService.delete(create_record_id, empcode);
		return new Response();
	}
	
	@ApiOperation(value="资料制作完成", response = Response.class)
	@PutMapping(value="/t_oa_test_create_records/{empcode}/{create_record_id}")
	public Response finish(@PathVariable Integer create_record_id) throws Exception {
		createRecordService.finish(create_record_id);
		return new Response();
	}
}
