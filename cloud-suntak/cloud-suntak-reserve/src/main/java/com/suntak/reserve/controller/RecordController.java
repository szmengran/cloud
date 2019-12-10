package com.suntak.reserve.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.suntak.exception.model.Response;
import com.suntak.reserve.entity.TReserveRecord;
import com.suntak.reserve.service.RecordService;

@RestController
@RequestMapping("/api/v1/reserve")
public class RecordController {

	@Autowired
	private RecordService recordService;
	
	/**
	 * 
	 * @description 根据openid查询来访登记记录
	 * @param openid
	 * @return
	 * @date Nov 6, 2019 1:19:20 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping("/record/{openid}/{pageNum}/{pageSize}")
	public Response findRecordByOpenid(@PathVariable("openid") String openid, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) throws Exception {
		if (pageSize > 100) {
			pageSize = 100;
		}
		PageHelper.startPage(pageNum, pageSize, "createstamp desc");
		List<TReserveRecord> list = recordService.findRecordByOpenid(openid);
		Response response = new Response();
		response.setData(list);
		return response;
	}
	
	@GetMapping("/record/{openid}")
	public Response findLastRecodeByOpenid(@PathVariable("openid") String openid) throws Exception {
		TReserveRecord tReserveRecord = recordService.findLastRecodeByOpenid(openid);
		Response response = new Response();
		response.setData(tReserveRecord);
		return response;
	}
	
	@PostMapping("/record/{openid}")
	public Response insert(@RequestBody TReserveRecord tReserveRecord) throws IllegalArgumentException, JsonProcessingException, IOException, Exception {
		if (tReserveRecord.getContact_phone() == null || tReserveRecord.getContact_phone().length() != 11) {
			throw new IllegalArgumentException("联系人电话号码不正确！");
		}
		try {
			Boolean flag = recordService.insert(tReserveRecord);
			if (!flag) {
				Response response = new Response();
				response.setStatus(10022002);
				response.setMessage("在人资系统");
				return response;
			}
		} catch (IllegalArgumentException e) {
			Response response = new Response();
			response.setStatus(10022001);
			response.setMessage(e.getMessage());
			return response;
		}
		return new Response();
	}
}
