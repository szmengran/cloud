package com.suntak.cloud.recruitment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.recruitment.client
 * @Description: 用户权限管理服务
 * @date 2018年9月12日 上午9:46:45
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "cloud-admin-user")
public interface UserServiceClient {
	
	@GetMapping("/api/v1/cloud-admin-user/user/{assignrole}")
	public Response findUserByRole(@PathVariable("assignrole") String assignrole) throws Exception;
	
	@GetMapping("/api/v1/cloud-admin-user/role/{username}")
	public Response findRoleByUsername(@PathVariable("username") String username) throws Exception;
}
