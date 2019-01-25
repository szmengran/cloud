package com.suntak.cloud.ehr.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.suntak.cloud.ehr.entity.Contact;
import com.suntak.cloud.ehr.entity.ContactResponse;

/**
 * @Package com.suntak.cloud.ehr.client
 * @Description: TODO
 * @date Dec 17, 2018 10:30:51 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "qyapi", url = "https://qyapi.weixin.qq.com")
public interface ConstactClient {

	@PostMapping("/cgi-bin/user/create?access_token={access_token}")
	ContactResponse createContact(@PathVariable("access_token") String access_token, @RequestBody Contact contact) throws Exception;
	
	@PostMapping("/cgi-bin/user/update?access_token={access_token}")
	ContactResponse updateContact(@PathVariable("access_token") String access_token, @RequestBody Contact contact) throws Exception;
	
	@GetMapping("/cgi-bin/user/delete?access_token={access_token}&userid={userid}")
	ContactResponse deleteContact(@PathVariable("access_token") String access_token, @PathVariable("userid") String userid) throws Exception;
	
	@GetMapping("/cgi-bin/user/get?access_token={access_token}&userid={userid}")
	Contact getContact(@PathVariable("access_token") String access_token, @PathVariable("userid") String userid) throws Exception;
}
