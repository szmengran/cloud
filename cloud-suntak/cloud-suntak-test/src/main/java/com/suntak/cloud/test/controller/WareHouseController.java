package com.suntak.cloud.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.test.entity.T_oa_test_warehouse;
import com.suntak.cloud.test.service.WareHouseService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;

/**
 * @Package com.suntak.cloud.test.controller
 * @Description: 库位管理
 * @date 2018年6月12日 下午2:07:11
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "t_oa_test_warehouse")
@RestController
@RequestMapping(path = "/api/v1/test", produces = { "application/json" })
public class WareHouseController {
	
	@Autowired
	WareHouseService wareHouseService;
	
	@PostMapping(value = "/t_oa_test_warehouses")
	public Response insert (@RequestBody T_oa_test_warehouse t_oa_test_warehouse) throws Exception {
		wareHouseService.insert(t_oa_test_warehouse);
		return new Response();
	}
	
	@DeleteMapping(value = "/t_oa_test_warehouses/{warehouse_code}")
	public Response delete (@PathVariable("warehouse_code") String warehouse_code) throws Exception {
		wareHouseService.delete(warehouse_code);
		return new Response();
	}
}
