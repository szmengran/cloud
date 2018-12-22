package com.suntak.cloud.ehr.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.ehr.service.ContactService;
import com.suntak.cloud.ehr.service.EhrUserService;
import com.suntak.ehr.entity.EhrUser;
import com.suntak.exception.model.Response;
import com.szmengran.admin.user.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @Package com.suntak.cloud.ehr.controller
 * @Description: 人事系统用户信息
 * @date 2018年4月11日 下午2:43:35
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "ehr")
@RestController
@RequestMapping(path = "/api/v1/ehr", produces = { "application/json" })
public class EhrUserController {
	
	@Autowired
	@Qualifier("ehrUserService")
	EhrUserService ehrUserService;
	
	@Autowired
	ContactService contactService;
	
	@ApiOperation(value = "获取生日的员工信息", response = Response.class)
	@GetMapping("birthdayusers/{monthdate}")
	public Response findBirthdayFromEhrUser(@PathVariable String monthdate) throws Exception {
		Response response = new Response();
		if(StringUtils.isBlank(monthdate.replace("*", ""))) {
			monthdate = new SimpleDateFormat("MM-dd").format(new Date());
		}
		StringBuffer conditions = new StringBuffer();
		conditions.append("empstatusname='在职' and to_char(c_birth_date, 'mm-dd')='")
		.append(monthdate).append("'");
		List<EhrUser> list = ehrUserService.findByCondition(conditions.toString());
		response.setData(list);
		return response;
	}
	
	/**
	 * 根据日期查找入职满整年的员工
	 * @param monthdate
	 * @return
	 * @throws Exception      
	 * @return: Response      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@ApiOperation(value = "根据日期查找入职满整年的员工", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@GetMapping("onboardusers/{monthdate}")
	public Response findOnboardFromEhrUser(@PathVariable String monthdate) throws Exception {
		Response response = new Response();
		if (StringUtils.isBlank(monthdate.replace("*", ""))) {
			monthdate = new SimpleDateFormat("MM-dd").format(new Date());
		}
		StringBuffer conditions = new StringBuffer();
		conditions.append("empstatusname='在职' and labordate < sysdate-1 and to_char(labordate, 'mm-dd')='")
		.append(monthdate).append("'");
		List<EhrUser> list = ehrUserService.findByCondition(conditions.toString());
		response.setData(list);
		return response;
	}
	
	@ApiOperation(value = "根据手机号查找用户信息", response = Response.class)
	@GetMapping("user/{phone}")
	public Response findEhrUserByPhone(@PathVariable("phone") String phone) throws Exception {
		Response response = new Response();
		StringBuffer conditions = new StringBuffer();
		conditions.append("empstatusname='在职' and C_MOBILE_TEL='").append(phone).append("'");
		List<EhrUser> list = ehrUserService.findByCondition(conditions.toString());
		response.setData(list);
		return response;
	}
	
	@ApiOperation(value = "根据工号查找EHR用户的信息", response = Response.class)
	@GetMapping(value = "/users/{empcode}")
	public Response getEhrUser(@PathVariable("empcode") String empcode) throws Exception{
		Response response = new Response();
		StringBuffer conditions = new StringBuffer();
		conditions.append("empstatusname='在职' and empcode=?");
		Object[] params = new Object[1];
		params[0] = empcode;
		List<EhrUser> list = ehrUserService.findByCondition(conditions.toString(), params);
		if (list != null && list.size() > 0) {
			response.setData(list.get(0));
		} else {
			throw new BusinessException(4000);
		}
		return response;
	}
	
	@ApiOperation(value = "根据名字查找EHR用户的信息", response = Response.class)
	@GetMapping(value = "/users/name/{name}")
	public Response getEhrUserByName(@PathVariable("name") String name) throws Exception{
		Response response = new Response();
		StringBuffer conditions = new StringBuffer();
		conditions.append("empstatusname='在职' and empname like '").append(name).append("%' and rownum <= 30");
		List<EhrUser> list = ehrUserService.findByCondition(conditions.toString());
		if (list != null && list.size() > 0) {
			response.setData(list);
		} else {
			throw new BusinessException(4000);
		}
		return response;
	}
	
	@ApiOperation(value = "更新员工的信息到企业微信通讯录", response = Response.class)
	@PutMapping(value = "/contact")
	public Response synchContact() throws Exception {
		contactService.synchContact();
		return new Response();
	}
	
}
