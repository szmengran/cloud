package com.suntak.cloud.haiwd.utils;
/**
 * @Package com.suntak.cloud.haiwd.utils
 * @Description: TODO
 * @date Jan 11, 2019 3:57:14 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class DatabaseContextHolder {
	
	private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

	public static void setDatabaseType(DatabaseType type){
		contextHolder.set(type);
	}

	public static DatabaseType getDatabaseType(){
		return contextHolder.get();
	}
}
