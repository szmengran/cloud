package com.suntak.cloud.test.controller.v2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.test.entity.T_oa_test_stand;
import com.suntak.cloud.test.entity.ext.T_oa_test_stand_ext;
import com.suntak.cloud.test.service.TestStandService;
import com.suntak.cloud.test.service.UseRecordService;
import com.suntak.cloud.test.util.ExcelTool;
import com.suntak.exception.model.Response;
import com.suntak.admin.user.exception.BusinessException;

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
@RequestMapping(path = "/api/test", produces = { "application/json" })
public class TestStandController2 {

	@Autowired
	@Qualifier("testStandService")
	private TestStandService testStandService;
	
	@Autowired
	@Qualifier("useRecordService")
	private UseRecordService useRecordService;
	
	@ApiOperation(value = "根据型号查询测试架", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@GetMapping(value = "/v2/t_oa_test_stands/{companycode}/{test_stand_code}")
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
	

	@GetMapping(value = "/v1/teststand/download/{companyCode}")
	public void download(@PathVariable("companyCode") String companyCode,  HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<T_oa_test_stand_ext> listData = testStandService.findByCompanyCode(companyCode);
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("listdata", listData);
		InputStream in = ExcelTool.exportExcel("/testmodule.xls", map);
    	String filename = transferCompanyCode(companyCode)+"-测试架记录报表.xls";
    	if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
    		filename = URLEncoder.encode(filename, "UTF-8");
    	} else {	
    		filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
    	}
    	response.setContentType("application/force-download");
    	response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

        OutputStream outStream = response.getOutputStream();
		BufferedInputStream bin = new BufferedInputStream(in);
		BufferedOutputStream bout = new BufferedOutputStream(outStream);
		while (true) {
			int datum = bin.read();
			if (datum == -1)
				break;
			bout.write(datum);
		}
		// 刷新缓冲区  
		bout.close();
		outStream.close(); 
		bin.close();
		in.close(); 
		response.flushBuffer();  
	}
	
	private String transferCompanyCode(String companyCode) {
		if ("0012".equals(companyCode)) {
			return "深圳";
		} else if ("0035".equals(companyCode)) {
			return "江门一厂";
		} else if ("0063".equals(companyCode)) {
			return "江门二厂";
		} else if ("0071".equals(companyCode)) {
			return "大连";
		} else {
			return companyCode;
		}
	}
}