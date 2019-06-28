package com.suntak.autotask.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间辅助类 - 20190216
 * @author zzhong
 *
 */
public class DateUtil {
	
	/**
	 * 获取固定格式的时间字符串 - ZZHONG20190216
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String getFormatDateStr(Date date,String formatStr){
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.format(date);
	}
	
	/**
	 * 获取今天在当月的表示 - ZZHONG20190216
	 * @return
	 */
	public static String getTodayOfMonth(){
		Calendar calendar = Calendar.getInstance();
		String today = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		return today;
	}
}
