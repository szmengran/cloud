package com.suntak.push.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.suntak.exception.model.Response;
import com.suntak.push.entity.CuxSoaWipPush;
import com.suntak.push.service.WipPushService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "工序工单停留提醒发送")
@RestController
@RequestMapping("/api/v1/push")
public class WipPushController {

	@Autowired
	private WipPushService wipPushService;

	/**
	 * 
	 * @description 查看工单停留信息
	 * @param attribute30
	 * @return
	 * @date Sep 30, 2019 3:47:28 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@ApiOperation(value = "查看工单停留信息", response = Response.class)
	@GetMapping("/wipinfo/{attribute30}")
	public Response findBySql(@PathVariable("attribute30") String attribute30) {
		PageHelper.startPage(1, 200, "stop_time desc");
		List<CuxSoaWipPush> list = wipPushService.findBySeq(attribute30);
		Response response = new Response();
		response.setData(list);
		return response;
	}
}
