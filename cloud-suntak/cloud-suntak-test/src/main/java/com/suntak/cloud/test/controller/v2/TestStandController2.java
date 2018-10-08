package com.suntak.cloud.test.controller.v2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(path = "/api/v2/test", produces = { "application/json" })
public class TestStandController2 {

	@Autowired
	@Qualifier("testStandService")
	private TestStandService testStandService;
	
	@Autowired
	@Qualifier("useRecordService")
	private UseRecordService useRecordService;
	
	@ApiOperation(value = "根据型号查询测试架", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@GetMapping(value = "/t_oa_test_stands/{companycode}/{test_stand_code}")
	public Response findByWipPrimaryItem(@PathVariable("companycode") String companycode, @PathVariable("test_stand_code") String wip_primary_item) throws Exception {
		StringBuffer conditions = new StringBuffer();
		conditions.append("and test_stand_code=? and companycode=? and validstatus!='0'");
		Object[] params = new Object[2];
		params[0] = wip_primary_item;
		params[1] = companycode;
		List<T_oa_test_stand> t_oa_test_stands = testStandService.findByConditions(conditions, params);
		Response response = new Response();
		if(t_oa_test_stands != null && t_oa_test_stands.size()>0) {
			response.setData(t_oa_test_stands);
		} else {
			throw new BusinessException(6002);
		}
		return response;
	}
}
