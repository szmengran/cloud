package com.suntak.cloud.test.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.test.entity.T_oa_test_stand;
import com.suntak.cloud.test.service.TestStandService;
import com.suntak.cloud.test.service.UseRecordService;
import com.suntak.exception.model.Response;
import com.szmengran.admin.user.exception.BusinessException;

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

	private final static Logger logger = LoggerFactory.getLogger(WareHouseController.class);
	
	@Autowired
	@Qualifier("testStandService")
	private TestStandService testStandService;
	
	@Autowired
	@Qualifier("useRecordService")
	private UseRecordService useRecordService;
	
	@ApiOperation(value = "根据型号查询测试架", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@GetMapping(value = "/t_oa_test_stands/{test_stand_code}")
	public Response findByWipPrimaryItem(@PathVariable("test_stand_code") String wip_primary_item) throws Exception {
		StringBuffer conditions = new StringBuffer();
		conditions.append("and test_stand_code=?");
		Object[] params = new Object[1];
		params[0] = wip_primary_item;
		List<T_oa_test_stand> t_oa_test_stands = testStandService.findByConditions(conditions, params);
		Response response = new Response();
		if(t_oa_test_stands != null && t_oa_test_stands.size()>0) {
			response.setData(t_oa_test_stands);
		} else {
			throw new BusinessException(6002);
		}
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
	@ApiOperation(value = "领用测试架", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@PutMapping(value = "/t_oa_test_stands/{empcode}/{test_stand_code}/{num}")
	public Response inuse(@PathVariable("empcode") String empcode, @PathVariable("test_stand_code") String test_stand_code, @PathVariable("num") Integer num) throws Exception {
		logger.info("inuse:{}, {}, {}", empcode, test_stand_code, num);
		testStandService.inuse(empcode, test_stand_code, num);
		return new Response();
	}
	
	@PutMapping(value = "/t_oa_test_stands/{empcode}/{test_stand_code}/{warehouse_code}/{num}")
	public Response giveback(@PathVariable("empcode") String empcode, @PathVariable("test_stand_code") String test_stand_code, @PathVariable("warehouse_code") String warehouse_code, @PathVariable("num") Integer num) throws Exception {
		logger.info("giveback:{}, {}, {}, {}", empcode, test_stand_code, warehouse_code, num);
		testStandService.giveback(test_stand_code, warehouse_code, num);
		return new Response();
	}
	
	/**
	 * 插入新测试架
	 * @param t_oa_test_stand
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping(value = "/t_oa_test_stands/{empcode}")
	public Response insert(@RequestBody T_oa_test_stand t_oa_test_stand, @PathVariable("empcode") String empcode) throws Exception {
		logger.info("insert:{}, empcode:{}", t_oa_test_stand, empcode);
		testStandService.insert(t_oa_test_stand);
		return new Response();
	}
	
	/**
	 * 作废测试架
	 * @param test_stand_code
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@DeleteMapping(value = "/t_oa_test_stands/{empcode}/{test_stand_code}/{num}")
	public Response invalid(@PathVariable("test_stand_code") String test_stand_code, @PathVariable("empcode") String empcode, @PathVariable("num") Integer num) throws Exception {
		logger.info("invalid:{}, empcode:{}", test_stand_code, empcode);
		testStandService.invalid(test_stand_code, num);
		return new Response();
	}
}
