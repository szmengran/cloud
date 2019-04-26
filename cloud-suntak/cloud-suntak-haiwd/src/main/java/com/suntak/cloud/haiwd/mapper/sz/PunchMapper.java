package com.suntak.cloud.haiwd.mapper.sz;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.punch.entity.Punch;
import com.szmengran.mybatis.utils.mapper.IMapper;

/**
 * @Package com.suntak.cloud.haiwd.mapper
 * @Description: 考勤数据查询
 * @date Jan 10, 2019 4:26:43 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface PunchMapper extends IMapper<Punch> {
	
	@SelectProvider(type = SqlProvider.class, method = "findWorkPunch")
	List<Punch> findWorkPunch(@Param("time") int time, @Param("date") String date, @Param("yearmonth") String yearmonth, @Param("resultdate") String resultdate, @Param("runnos") String runnos) throws Exception;
	
	/**
	 * 上班时间提前N分钟执行提醒
	 * @param beforeMinute
	 * @param scanSecond 扫描间隔多少秒
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@SelectProvider(type = SqlProvider.class, method = "findWorkRunno1")
	List<Punch> findWorkRunno1(@Param("beforeMinute") int beforeMinute, @Param("scanSecond") int scanSecond) throws Exception;
	
	@SelectProvider(type = SqlProvider.class, method = "findWorkRunno3")
	List<Punch> findWorkRunno3(@Param("beforeMinute") int beforeMinute, @Param("scanSecond") int scanSecond) throws Exception;
	
	/**
	 * 下班N分钟后执行提醒
	 * @param beforeMinute
	 * @param scanSecond
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@SelectProvider(type = SqlProvider.class, method = "findOffRunno2")
	List<Punch> findOffRunno2(@Param("afterMinute") int beforeMinute, @Param("scanSecond") int scanSecond) throws Exception;
	
	@SelectProvider(type = SqlProvider.class, method = "findOffRunno4")
	List<Punch> findOffRunno4(@Param("afterMinute") int beforeMinute, @Param("scanSecond") int scanSecond) throws Exception;
	
	class SqlProvider {
		
		public String findWorkPunch(Map<String, Object> param){
			int time = (int) param.get("time");
			String date = (String) param.get("date");
			String runnos = (String) param.get("runnos");
			return new SQL(){
	            {
//	            	SELECT a."第一次打卡" punch1,a."第二次打卡" punch2,a."第三次打卡" punch3,a."第四次打卡" punch4,a."第五次打卡" punch3,a."第六次打卡" punch4,b."EmpNo" empno,
//	            	 c."Result2AdjustInTime" punchin1, c."Result2AdjustOutTime" punchout1, 
//	            	       c."Result3AdjustInTime" punchin2, c."Result3AdjustOutTime" punchout2, 
//	            	       c."Result5AdjustInTime" punchin3, c."Result5AdjustOutTime" punchout3
//	            	  FROM emp_kq_paiban@kqdb.suntakpcb.com a, 
//	            	  emp_kq_runs@kqdb.suntakpcb.com b left join VKQ_Result@kqdb.suntakpcb.com c on b."EmpSysID" = c."EmpSysID"
//	            	 where b."RunMonth" = '2019-01'
//	            	   and b."D09RunNo" = a."RunNo";
//	            	   and c."ResultDate" = '2019-01-08 00:00:00';
	                SELECT("b.EmpNo empno");
	                FROM("emp_kq_paiban a, emp_kq_runs b left join VKQ_Result c on b.EmpSysID = c.EmpSysID");
	                WHERE("b.D"+date+"RunNo = a.RunNo");
	                WHERE("b.RunMonth = #{yearmonth}");
	                WHERE("c.ResultDate = #{resultdate}");
	                switch(time) {
		                case 1:
		                	WHERE("c.Result2AdjustInTime is null");
		                	break;
		                case 2:
		                	WHERE("c.Result2AdjustOutTime is null");
		                	break;
		                case 3:
		                	WHERE("c.Result4AdjustInTime is null");
		                	break;
		                case 4:
		                	WHERE("c.Result4AdjustOutTime is null");
		                	break;
	                }
	                
	                WHERE("a.RunNo in ( "+runnos+" )");
	                WHERE("b.EmpNo in ('000078','000357','001100','001123','001232','001263','001385','001389','001413','001599','002058','003144','004021','004071','004693','004835','005986','006124','006204','006238','007795','009068','010075','010105','010257','010270','010689','010847','010889')");
	            }
	        }.toString();
	    }
		
		public String findWorkRunno1(Map<String, Object> param){
			Integer beforeMinute = (Integer) param.get("beforeMinute");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, beforeMinute);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			String start = (hour > 9 ? hour : "0"+hour)+":"+(minute > 9 ? minute : "0"+minute)+":00";

			Integer scanSecond = (Integer) param.get("scanSecond");
			calendar.add(Calendar.SECOND, scanSecond);
			
			hour = calendar.get(Calendar.HOUR_OF_DAY);
			minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			String end = (hour > 9 ? hour : "0"+hour)+":"+(minute > 9 ? minute : "0"+minute)+":"+(second > 9 ? second : "0"+second);
			
			return new SQL(){
				{
					SELECT("RunNo runno");
					FROM("emp_kq_paiban");
					WHERE("第一次打卡 != '00:00:00'");
					WHERE("第一次打卡 between '"+start+"' and '"+end+"'");
				}
			}.toString();
		}
		
		public String findWorkRunno3(Map<String, Object> param){
			Integer beforeMinute = (Integer) param.get("beforeMinute");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, beforeMinute);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			String start = (hour > 9 ? hour : "0"+hour)+":"+(minute > 9 ? minute : "0"+minute)+":00";
			
			Integer scanSecond = (Integer) param.get("scanSecond");
			calendar.add(Calendar.SECOND, scanSecond);
			
			hour = calendar.get(Calendar.HOUR_OF_DAY);
			minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			String end = (hour > 9 ? hour : "0"+hour)+":"+(minute > 9 ? minute : "0"+minute)+":"+(second > 9 ? second : "0"+second);
			
			return new SQL(){
				{
					SELECT("RunNo runno");
					FROM("emp_kq_paiban");
					WHERE("第三次打卡 != '00:00:00'");
					WHERE("第三次打卡 between '"+start+"' and '"+end+"'");
				}
			}.toString();
		}
		
		public String findOffRunno2(Map<String, Object> param){
			Integer afterMinute = (Integer) param.get("afterMinute");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, -1*afterMinute);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			String end = (hour > 9 ? hour : "0"+hour)+":"+(minute > 9 ? minute : "0"+minute)+":00";
			
			Integer scanSecond = (Integer) param.get("scanSecond");
			calendar.add(Calendar.SECOND, -1*scanSecond);
			
			hour = calendar.get(Calendar.HOUR_OF_DAY);
			minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			String start = (hour > 9 ? hour : "0"+hour)+":"+(minute > 9 ? minute : "0"+minute)+":"+(second > 9 ? second : "0"+second);
			
			return new SQL(){
				{
					SELECT("RunNo runno");
					FROM("emp_kq_paiban");
					WHERE("第二次打卡 != '00:00:00'");
					WHERE("第二次打卡 between '"+start+"' and '"+end+"'");
				}
			}.toString();
		}
		
		public String findOffRunno4(Map<String, Object> param){
			Integer afterMinute = (Integer) param.get("afterMinute");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, -1*afterMinute);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			String end = (hour > 9 ? hour : "0"+hour)+":"+(minute > 9 ? minute : "0"+minute)+":00";
			
			Integer scanSecond = (Integer) param.get("scanSecond");
			calendar.add(Calendar.SECOND, -1*scanSecond);
			
			hour = calendar.get(Calendar.HOUR_OF_DAY);
			minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			String start = (hour > 9 ? hour : "0"+hour)+":"+(minute > 9 ? minute : "0"+minute)+":"+(second > 9 ? second : "0"+second);
			
			return new SQL(){
				{
					SELECT("RunNo runno");
					FROM("emp_kq_paiban");
					WHERE("第四次打卡 != '00:00:00'");
					WHERE("第四次打卡 between '"+start+"' and '"+end+"'");
				}
			}.toString();
		}
	}
}
