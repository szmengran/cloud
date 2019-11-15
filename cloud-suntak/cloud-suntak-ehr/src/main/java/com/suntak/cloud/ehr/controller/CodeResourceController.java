package com.suntak.cloud.ehr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.ehr.entity.CodeResource;
import com.suntak.cloud.ehr.service.CodeResourceService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;

@Api(value = "获取Code Resource")
@RestController
public class CodeResourceController {

	@Autowired
	private CodeResourceService codeResourceService;
	
	/**
	 * 
	 * @description 查找员工的入职信息
	 * @param c_type_code
	 * @return
	 * @date Nov 14, 2019 11:31:48 AM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping("/coderesource/{c_type_code}")
	public Response findResource(@PathVariable("c_type_code") String c_type_code) {
		List<CodeResource> list = codeResourceService.findResource(c_type_code);
		Response response = new Response();
		response.setData(list);
		return response;
	}
}
