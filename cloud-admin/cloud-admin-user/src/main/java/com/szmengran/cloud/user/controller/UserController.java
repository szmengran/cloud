package com.szmengran.cloud.user.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.exception.model.Response;
import com.szmengran.cloud.user.service.UserService;

import io.swagger.annotations.Api;

@Api(value = "user")
@RestController
@RequestMapping(path = "/api/v1/cloud-admin-user", produces = { "application/json" })
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
}
