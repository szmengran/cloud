package com.suntak.cloud.ehr.client;
/**
 * @Package com.suntak.cloud.ehr.client
 * @Description: TODO
 * @date Dec 17, 2018 11:12:31 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.suntak.cloud.ehr.entity.DepartmentResponse;

@FeignClient(name = "qyapi", url = "https://qyapi.weixin.qq.com")
public interface DepartmentClient {

	@GetMapping("/cgi-bin/department/list?access_token={access_token}")
	DepartmentResponse getDepartment(@PathVariable("access_token") String access_token) throws Exception;
}
