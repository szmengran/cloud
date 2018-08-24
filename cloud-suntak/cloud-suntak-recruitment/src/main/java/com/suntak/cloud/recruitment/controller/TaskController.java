package com.suntak.cloud.recruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.recruitment.entity.T_hr_task;
import com.suntak.cloud.recruitment.entity.ext.T_hr_task_ext;
import com.suntak.cloud.recruitment.service.TaskService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;

/**
 * @Package com.suntak.cloud.recruitment.controller
 * @Description: TODO
 * @date 2018年8月22日 上午9:23:22
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "任务服务")
@RestController
@RequestMapping(value="/api/v1/recruitment")
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@PostMapping("/task")
	public Response insert(@RequestBody T_hr_task t_hr_task) throws Exception {
		taskService.insert(t_hr_task);
		return new Response();
	}
	
	@GetMapping("/task/{roles}/{userid}")
	public Response find(@PathVariable("userid") Integer userid, @PathVariable("roles") String strRole) throws Exception {
		List<T_hr_task_ext> list = taskService.find(strRole.split(","), userid);
		Response response = new Response();
		response.setData(list);
		return response;
	}
}
