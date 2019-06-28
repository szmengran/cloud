package com.suntak.autotask.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * 连接数据库辅助类 - 2019-02-14
 * @author zzhong
 *
 */
public class DbUtil {

	public static String quoJNDI = "/jdbc/Quote";// 报价系统jndi
	public static String erpJNDI = "/jdbc/Erp";// erp生产环境jndi
	public static String erp8006JNDI = "/jdbc/Erp8006";// erp测试环境8006jndi
	public static String quoTestJNDI = "/jdbc/Quote_test";// 报价系统测试环境
	public static String reportJNDI = "/jdbc/Report";// 出货报告辅助程序库
	public static String oaTestJNDI = "/jdbc/OATest";//OA测试环境
	public static String oaProdJNDI = "//jdbc/OAPord";//OA生产环境

	/**
	 * 通过jndi方式获得数据库连接池连接
	 * 
	 * @param jndiStr
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection(String jndiStr) throws Exception {
		Connection conn = null;
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env" + jndiStr);
		conn = ds.getConnection();
		return conn;
	}
	
	
	public static Connection getConnectionTest() throws Exception{
        String driver = "oracle.jdbc.OracleDriver";    //驱动标识符
        String url = "jdbc:oracle:thin:@10.1.100.158:1521/oadb"; //链接字符串
        // url ="jdbc:oracle:thin:@10.0.30.64:1521:orcl";  // 连接远程的数据库可以这么写
        String user = "v3xuser";         //数据库的用户名
        String password = "v3xuser";     //数据库的密码
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url,user, password);
		return conn;
	}

}
