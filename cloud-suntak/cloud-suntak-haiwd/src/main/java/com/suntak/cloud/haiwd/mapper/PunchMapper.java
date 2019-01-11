package com.suntak.cloud.haiwd.mapper;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.cloud.haiwd.entity.Punch;
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
	List<Punch> findWorkPunch(String date, String yearmonth, String resultdate) throws Exception;
	
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
	                WHERE("b.D#{date}RunNo = a.RunNo");
	                WHERE("b.RunMonth = #{yearmonth}");
	                WHERE("c.ResultDate = #{resultdate}");
	                WHERE("(c.Result2AdjustInTime null or c.Result3AdjustInTime is null)");
	                WHERE("a.RunNo in #{runnos}");
	            }
	        }.toString();
	    }
		
		public String findWorkRunno1(Map<String, Object> param){
			Integer beforeMinute = (Integer) param.get("beforeMinute");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, beforeMinute);
			int hour = calendar.get(Calendar.HOUR);
			int minute = calendar.get(Calendar.MINUTE);
			String start = hour+":"+minute+":00";

			Integer scanSecond = (Integer) param.get("scanSecond");
			calendar.add(Calendar.SECOND, scanSecond);
			
			hour = calendar.get(Calendar.HOUR);
			minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			String end = hour+":"+minute+":"+second;
			
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
			int hour = calendar.get(Calendar.HOUR);
			int minute = calendar.get(Calendar.MINUTE);
			String start = hour+":"+minute+":00";
			
			Integer scanSecond = (Integer) param.get("scanSecond");
			calendar.add(Calendar.SECOND, scanSecond);
			
			hour = calendar.get(Calendar.HOUR);
			minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			String end = hour+":"+minute+":"+second;
			
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
			int hour = calendar.get(Calendar.HOUR);
			int minute = calendar.get(Calendar.MINUTE);
			String start = hour+":"+minute+":00";
			
			Integer scanSecond = (Integer) param.get("scanSecond");
			calendar.add(Calendar.SECOND, scanSecond);
			
			hour = calendar.get(Calendar.HOUR);
			minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			String end = hour+":"+minute+":"+second;
			
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
			int hour = calendar.get(Calendar.HOUR);
			int minute = calendar.get(Calendar.MINUTE);
			String start = hour+":"+minute+":00";
			
			Integer scanSecond = (Integer) param.get("scanSecond");
			calendar.add(Calendar.SECOND, scanSecond);
			
			hour = calendar.get(Calendar.HOUR);
			minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			String end = hour+":"+minute+":"+second;
			
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
