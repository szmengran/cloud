//package com.sqlserver.test;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//import org.junit.Test;
//
///**
// * @Package com.sqlserver.test
// * @Description: TODO
// * @date Dec 27, 2018 4:08:16 PM
// * @author <a href="mailto:android_li@sina.cn">Joe</a>
// */
//public class SqlServerTest {
//	
//	@Test
//	public void getConnection(){
//		Connection conn = null;
//		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			System.out.println("数据库驱动加载成功");
//			conn=DriverManager.getConnection("jdbc:sqlserver://192.168.0.215:1433;DatabaseName=CenterSix_Suntak","sa","123qwe@");
//			if(conn==null){
//				System.out.println("数据库连接失败");
//				System.out.println("-----------------------");
//			}else {
//				PreparedStatement pstmt = conn.prepareStatement("{call dbo.udprpt_XFTotalByEmp(?,?,?)}"); 
//				pstmt.setString(1, "2018-11-01"); 
//				pstmt.setString(2, "2018-11-30"); 
//				pstmt.setString(3, "006124"); 
//				ResultSet rs = pstmt.executeQuery(); 
//				while (rs.next()) { 
//					System.out.println("EMPLOYEE:"); 
//					System.out.println(rs.getString("empname")); 
//					System.out.println(rs.getString("XFZaoCanNum")); 
//					System.out.println(rs.getString("XFZaoCan")); 
//				} 
//				rs.close(); 
//				pstmt.close(); 
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//
//	public static void main(String args[]) {
//		
//	}
//}
