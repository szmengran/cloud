package com.szmengran.cloud.user.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.exception.model.Response;
import com.szmengran.admin.entity.T_power_user;
import com.szmengran.admin.entity.ext.T_power_user_ext;
import com.szmengran.cloud.user.service.UserService;

import io.swagger.annotations.Api;

@Api(value = "user")
@RestController
@RequestMapping(path = "/api/v1/cloud-admin-user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PatchMapping(value="/user/{password}/{oldPassword}")
	Response user(Principal principal, @PathVariable String password, @PathVariable String oldPassword) throws Exception {
		Response response = new Response();
		String userid = principal.getName();
		userService.updatePwd(userid, password, oldPassword);
		response.setData("密码修改成功");
		return response;
	}
	
	@PostMapping("/login")
	public Response login(@RequestBody T_power_user t_power_user) throws Exception{
		T_power_user_ext t_power_user_ext = userService.login(t_power_user.getUsername(), t_power_user.getPassword());
		Response response = new Response();
		response.setData(t_power_user_ext);
		return response;
	}
	
	@GetMapping("/user/{assignrole}")
	public Response findUserByRole(@PathVariable("assignrole") String assignrole) throws Exception {
		List<T_power_user> list = userService.findUserByRole(assignrole);
		Response response = new Response();
		response.setData(list);
		return response;
	}
}
