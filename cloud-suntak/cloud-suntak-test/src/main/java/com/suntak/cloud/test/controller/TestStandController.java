package com.suntak.cloud.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.test.entity.T_oa_test_stand;
import com.suntak.cloud.test.service.TestStandService;
import com.suntak.cloud.test.service.UseRecordService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @Package com.suntak.cloud.test.controller
 * @Description: 测试架
 * @date 2018年6月11日 下午4:23:08
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "t_oa_test_stand")
@RestController
@RequestMapping(path = "/api/v1/test", produces = { "application/json" })
public class TestStandController {
	
	@Autowired
	@Qualifier("testStandService")
	private TestStandService testStandService;
	
	@Autowired
	@Qualifier("useRecordService")
	private UseRecordService useRecordService;
	
	@ApiOperation(value = "根据型号查询测试架", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@GetMapping(value = "/t_oa_test_stands/{wip_primary_item}")
	public Response findByWipPrimaryItem (@PathVariable("wip_primary_item") String wip_primary_item) throws Exception {
		StringBuffer conditions = new StringBuffer();
		conditions.append("and wip_primary_item=?");
		Object[] params = new Object[1];
		params[0] = wip_primary_item;
		List<T_oa_test_stand> t_oa_test_stands = testStandService.findByConditions(conditions, params);
		Response response = new Response();
		response.setData(t_oa_test_stands.get(0));
		return response;
	}
	
	/**
	 * 领用测试架
	 * @param empcode
	 * @param test_stand_code
	 * @param validstatus
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PatchMapping(value = "/t_oa_test_stands/{empcode}/{test_stand_code}")
	public Response inuse (@PathVariable("empcode") String empcode, @PathVariable("test_stand_code") String test_stand_code, @PathVariable("validstatus") String validstatus) throws Exception {
		testStandService.inuse(empcode, test_stand_code);
		return new Response();
	}
	
	@PostMapping(value = "/t_oa_test_stands")
	public Response insert (@RequestBody T_oa_test_stand t_oa_test_stand) throws Exception {
		testStandService.insert(t_oa_test_stand);
		return new Response();
	}
	
}
