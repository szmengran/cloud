package com.suntak.report.efficiency.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.DruidDataSource;
import com.suntak.report.efficiency.entity.T_report_monitor;
import com.suntak.report.efficiency.service.MonitorService;
import com.szmengran.common.orm.DBManager;
import com.szmengran.common.orm.dao.oracle.OracleDao;

@Service
public class MonitorServiceImpl extends OracleDao implements MonitorService {

	private static final Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);
	
	@Resource
	private DruidDataSource writeDataSource;

	@CacheEvict(allEntries = true, value = "MonitorServiceImpl.findYearMonthWorktime")
	@Scheduled(fixedDelay = 90 * 60 * 1000 ,  initialDelay = 500)
	private void findByConditionsCacheEvict() {
		logger.info("remove cache");
	}
	
	@Cacheable(value = "MonitorServiceImpl.findByConditions", key = "#p0")
	@Override
	public List<T_report_monitor> findByConditions(String key, StringBuffer conditions, Object[] params) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.process,t.companyname,sum(t.peoples) peoples,sum(t.outpnl_area) outpnl_area,sum(t.outcp_area) outcp_area,")
		   .append(" sum(t.worktime) worktime,sum(t.spendtime) spendtime,(")
		   .append(" SELECT distinct a.standard FROM t_report_process_mapping a left join t_report_company_r_org b on a.companycode=b.companycode ")
		   .append(" where a.yearmonth=substr(t.calculation_date,1,7)")
		   .append(" and b.org_id=t.organization_id and a.process=t.process ) standard  from T_REPORT_MONITOR t")
		   .append(" left join t_report_displayno d on t.process = d.process")
		   .append(" where 1=1 ")
		   .append(conditions)
		   .append(" group by t.process,t.companyname,t.organization_id,substr(t.calculation_date,1,7),d.displayno order by d.displayno");
		return super.findBySql(T_report_monitor.class, sql.toString(), params);
	}

	private static String getLastDayOfMonth(int year,int month){  
        Calendar cal = Calendar.getInstance();  
        //设置年份  
        cal.set(Calendar.YEAR,year);  
        //设置月份  
        cal.set(Calendar.MONTH, month-1);  
        //获取某月最大天数  
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
        //设置日历中月份的最大天数  
        cal.set(Calendar.DAY_OF_MONTH, lastDay);  
        //格式化日期  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String lastDayOfMonth = sdf.format(cal.getTime());  
        return lastDayOfMonth;  
    }
	
	@CacheEvict(allEntries = true, value = "MonitorServiceImpl.findYearMonthWorktime")
	@Scheduled(fixedDelay = 90 * 60 * 1000 ,  initialDelay = 500)
	private void findYearMonthWorktimeCacheEvict() {
		logger.info("remove cache");
	}
	
	@Cacheable(value = "MonitorServiceImpl.findYearMonthWorktime", key = "#p0")
	@Override
	public List<T_report_monitor> findYearMonthWorktime(String key, String date, Integer org_id) throws Exception {
		if (org_id == 84) {
			return findYearMonthWorktime_dl(date, org_id);
		} else {
			return findYearMonthWorktime_other(date, org_id);
		}
	}
	/**
	 * 大连月度报表数据查询
	 * @param date
	 * @param org_id
	 * @return
	 * @throws Exception
	 */
	private List<T_report_monitor> findYearMonthWorktime_dl(String date, Integer org_id) throws Exception {
		String end = date.substring(8,10);
		String startDate = "";
		if (end.equals("01")) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
			calendar.add(Calendar.MONTH, -1);
			startDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		} else {
			startDate = date.substring(0, 8)+"01";
		}
		
		StringBuffer sql = new StringBuffer();
		Object[] params = null;
		sql.append("select t.process,t.companyname,sum(t.peoples) peoples,sum(t.outpnl_area) outpnl_area,sum(t.outcp_area) outcp_area,")
		   .append(" sum(t.worktime) worktime,sum(t.spendtime) spendtime,(")
		   .append(" SELECT max(a.standard) FROM t_report_process_mapping a left join t_report_company_r_org b on a.companycode=b.companycode ")
		   .append(" where a.yearmonth='"+startDate.substring(0,7)+"'")
		   .append(" and b.org_id=t.organization_id and a.process=t.process ) standard  from T_REPORT_MONITOR t")
		   .append(" left join t_report_displayno d on t.process = d.process")
		   .append(" where t.calculation_time in ('08:00:00','20:00:00')")
		   .append(" and t.count_hour=12")
		   .append(" and t.organization_id=?")
		   .append(" and t.calculation_date between ? and ?")
		   .append(" and (t.calculation_time = '20:00:00' or t.calculation_date !=?)");
		if(end.equals("01")) { //月初时，20:00的数据不纳入上个月
			sql.append(" and (t.calculation_time = '08:00:00' or t.calculation_date !=?)");
			params = new Object[5];
			params[0] = org_id;
			params[1] = startDate;
			params[2] = date;
			params[3] = startDate;
			params[4] = date;
		}else {
			params = new Object[4];
			params[0] = org_id;
			params[1] = startDate;
			params[2] = date;
			params[3] = startDate;
		}
		sql.append(" group by t.process,t.companyname,t.organization_id,d.displayno order by d.displayno");
		
		return super.findBySql(T_report_monitor.class, sql.toString(), params);
	}
	
	/**
	 * 深圳和江门月度报表数据查询
	 * @param date
	 * @param org_id
	 * @return
	 * @throws Exception
	 */
	private List<T_report_monitor> findYearMonthWorktime_other(String date, Integer org_id) throws Exception {
		String startDate = date.substring(0, 8)+"01";
		int year = Integer.parseInt(date.substring(0,4));
		int month = Integer.parseInt(date.substring(5,7));
		String lastDateOfMonth = getLastDayOfMonth(year, month);
		StringBuffer sql = new StringBuffer();
		Object[] params = null;
		sql.append("select t.process,t.companyname,sum(t.peoples) peoples,sum(t.outpnl_area) outpnl_area,sum(t.outcp_area) outcp_area,")
		   .append(" sum(t.worktime) worktime,sum(t.spendtime) spendtime,(")
		   .append(" SELECT max(a.standard) FROM t_report_process_mapping a left join t_report_company_r_org b on a.companycode=b.companycode ")
		   .append(" where a.yearmonth='"+date.substring(0,7)+"'")
		   .append(" and b.org_id=t.organization_id and a.process=t.process ) standard  from T_REPORT_MONITOR t")
		   .append(" left join t_report_displayno d on t.process = d.process")
		   .append(" where t.calculation_time in ('08:00:00','20:00:00')")
		   .append(" and t.count_hour=12")
		   .append(" and t.organization_id=?")
		   .append(" and t.calculation_date between ? and ?");
		if(!date.equals(lastDateOfMonth)) {
			sql.append(" and to_date(t.calculation_date||' '||t.calculation_time,'YYYY-MM-DD HH24:mi:ss')<?");
			params = new Object[4];
			params[0] = org_id;
			params[1] = startDate;
			params[2] = date;
			params[3] = new Timestamp(new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).parse(date+" 20:00:00").getTime());
		}else {
			params = new Object[3];
			params[0] = org_id;
			params[1] = startDate;
			params[2] = date;
		}
		sql.append(" group by t.process,t.companyname,t.organization_id,d.displayno order by d.displayno");
		
		return super.findBySql(T_report_monitor.class, sql.toString(), params);
	}
	
	@CacheEvict(allEntries = true, value = "MonitorServiceImpl.findDayWorktime")
	@Scheduled(fixedDelay = 90 * 60 * 1000 ,  initialDelay = 500)
	private void findDayWorktimeCacheEvict() {
		logger.info("remove qywechatToken cache");
	}
	
	@Cacheable(value = "MonitorServiceImpl.findDayWorktime", key = "#p0")
	@Override
	public List<T_report_monitor> findDayWorktime(String key, String date, Integer org_id) throws Exception {
		Calendar calendar = Calendar.getInstance();
		String[] d = date.split("-");
		calendar.set(Integer.parseInt(d[0]), Integer.parseInt(d[1])-1, Integer.parseInt(d[2]));
		calendar.add(Calendar.DATE, -1);
		String startDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.process,t.companyname,sum(t.peoples) peoples,sum(t.outpnl_area) outpnl_area,sum(t.outcp_area) outcp_area,")
		.append(" sum(t.worktime) worktime,sum(t.spendtime) spendtime,(")
		.append(" SELECT distinct a.standard FROM t_report_process_mapping a left join t_report_company_r_org b on a.companycode=b.companycode ")
		.append(" where a.yearmonth='"+startDate.substring(0,7)+"'")
		.append(" and b.org_id=t.organization_id and a.process=t.process ) standard  from T_REPORT_MONITOR t")
		.append(" left join t_report_displayno d on t.process = d.process")
		.append(" where t.count_hour=12 ")
		.append(" and t.organization_id=?")
		.append(" and ((t.calculation_date=? and t.calculation_time = '20:00:00' ) or (t.calculation_date=? and t.calculation_time = '08:00:00' ))")
		.append(" group by t.process,t.companyname,t.organization_id,d.displayno order by d.displayno");
		Object[] params = new Object[3];
		params[0] = org_id;
		params[1] = startDate;
		params[2] = date;
		return super.findBySql(T_report_monitor.class, sql.toString(), params);
	}
	
	@Override
	public void getWorktimeMonitorData(String calculation_date, String calculation_time, Integer count) throws Exception {
		DBManager dbManager = null;
		try {
			dbManager = new DBManager(writeDataSource);
			dbManager.openConnection();
			dbManager.beginTransaction();
			dbManager.prepareCall("{call pkg_manufacturing_monitor.pro_collect_monitor_data(?,?,?)}");
			dbManager.setPrepareCallParameters(1, count);
			dbManager.setPrepareCallParameters(2, calculation_date);
			dbManager.setPrepareCallParameters(3, calculation_time);
			dbManager.executePrepareCall();
			dbManager.commitTransaction();
		} catch (Exception e) {
			dbManager.rollbackTransaction();
			throw e;
		} finally {
			dbManager.close();
		}
	}

}
