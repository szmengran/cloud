package com.suntak.mes.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.exception.model.Response;
import com.suntak.mes.entity.TMesCopperWire;
import com.suntak.mes.service.CopperWireService;

@RestController
@RequestMapping("/api/v1/copper")
public class CopperWireController {

	@Autowired
	private CopperWireService copperWireService;
	
	/**
	 * 
	 * @description 保存沉铜记录
	 * @param tMesCopperWire
	 * @return
	 * @date Nov 1, 2019 10:32:01 AM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 * @throws SQLException 
	 */
	public Response insert(@RequestBody TMesCopperWire tMesCopperWire) throws SQLException  {
		copperWireService.insert(tMesCopperWire);
		return new Response();
	}
}
