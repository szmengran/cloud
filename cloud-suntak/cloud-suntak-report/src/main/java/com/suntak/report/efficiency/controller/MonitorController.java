package com.suntak.report.efficiency.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.exception.model.Response;
import com.suntak.report.efficiency.entity.T_report_monitor;
import com.suntak.report.efficiency.service.MonitorService;

@RestController
public class MonitorController {
	
	@Autowired
	private MonitorService monitorService;
	
	/**
	 * 
	 * @description 工作效率班次数据查询
	 * @param count
	 * @param calculation_date
	 * @param calculation_time
	 * @param org_id
	 * @return
	 * @throws Exception
	 * @date Dec 17, 2019 2:42:25 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@RequestMapping(value = "/efficiency/worktime/{count}/{date}/{time}/{org_id}", method = RequestMethod.GET)
	public Response worktime_monitor(@PathVariable("count") Integer count,@PathVariable("date") String calculation_date,@PathVariable("time") String calculation_time,@PathVariable("org_id") Integer org_id) throws Exception {
		Response response = new Response();
		String key = "worktime_monitor"+count+calculation_date+calculation_time+org_id;
		StringBuffer conditions = new StringBuffer();
		conditions.append(" and calculation_date = ? and calculation_time = ? and count_hour = ? and organization_id = ?");
		Object[] params = new Object[4];
		params[0] = calculation_date;
		params[1] = calculation_time;
		params[2] = count;
		params[3] = org_id;
		List<T_report_monitor> list = monitorService.findByConditions(key, conditions, params);
		if (list != null) {
			response.setData(list);
		}
		return response;
	}

	/**
	 * 
	 * @description 工作效率月度报表
	 * @param date
	 * @param org_id
	 * @return
	 * @throws Exception
	 * @date Dec 17, 2019 2:43:06 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping(value = "/efficiency/findYearMonthWorktime/{date}/{org_id}")
	public Response findYearMonthWorktime(@PathVariable("date") String date,@PathVariable("org_id") Integer org_id) throws Exception {
		Response response = new Response();
		String key = "findYearMonthWorktime"+date+org_id;
		List<T_report_monitor> list = monitorService.findYearMonthWorktime(key, date, org_id);
		if (list != null) {
			response.setData(list);
		}
		return response;
	}
	
	/**
	 * 
	 * @description 工作效率日报表
	 * @param date
	 * @param org_id
	 * @return
	 * @throws Exception
	 * @date Dec 17, 2019 2:43:25 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping(value = "/efficiency/findDayWorktime/{date}/{org_id}")
	public Response findDayWorktime(@PathVariable("date") String date,@PathVariable("org_id") Integer org_id) throws Exception {
		Response response = new Response();
		String key = "findDayWorktime"+date+org_id;
		List<T_report_monitor> list = monitorService.findDayWorktime(key, date, org_id);
		if (list != null) {
			response.setData(list);
		}
		return response;
	}
	
	/**
	 * 
	 * @description 工作效率班次数据提取
	 * @param count
	 * @param date
	 * @return
	 * @throws Exception
	 * @date Dec 17, 2019 2:43:59 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping(value = "/efficiency/extractWorkData/{count}/{date}")
	public Response extractWorkData(@PathVariable("count") Integer count,@PathVariable("date") String date) throws Exception {
		Response response = new Response();
		if(StringUtils.isBlank(date.trim().replace("*", ""))){
			date = new SimpleDateFormat("yyyy-MM-dd HH").format(new Date());
			if(count == 12){ //12个小时的数据，也就是一个班次的数据
				if(Integer.parseInt(date.split(" ")[1]) < 20 && Integer.parseInt(date.split(" ")[1]) >= 8){ //当提取时间在08点到20点之间时，提取的是昨天20点到今天08点的数据
					date = date.split(" ")[0]+" 08";
				}else{
					date = date.split(" ")[0]+" 20"; //提取的是今天08点到今天20点的数据
				}
			}
		}
		String calculation_date = date.split(" ")[0];
		String calculation_time = date.split(" ")[1]+":00:00";
		monitorService.extractWorkData(calculation_date, calculation_time, count);
		return response;
	}
	
}
