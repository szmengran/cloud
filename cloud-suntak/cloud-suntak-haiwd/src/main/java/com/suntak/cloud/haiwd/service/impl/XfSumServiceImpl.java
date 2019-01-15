package com.suntak.cloud.haiwd.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.haiwd.service.XfSumService;
import com.suntak.cloud.haiwd.utils.DatabaseContextHolder;
import com.suntak.cloud.haiwd.utils.DatabaseType;
import com.suntak.punch.entity.XfSum;

/**
 * @Package com.suntak.cloud.microservices.payment.service.impl
 * @Description: TODO
 * @date Dec 27, 2018 5:08:22 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class XfSumServiceImpl implements XfSumService{
	
	@Autowired  
    private SqlSession sqlSession;
	
	public Connection getConnection(String companycode) throws Exception{
		Connection conn = null;
		if ("0071".equals(companycode)) {
			DatabaseContextHolder.setDatabaseType(DatabaseType.dldb);
			conn =  sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection(); 
		} else {
			DatabaseContextHolder.setDatabaseType(DatabaseType.szdb);
		}
		conn =  sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection(); 
		return conn;
	}
	
	@Override
	public XfSum findXfsumByEmpno(String empno, String companycode, String month) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = format.parse(month+"-01");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		String first = format.format(calendar.getTime());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = format.format(calendar.getTime());
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		XfSum xfSum = new XfSum();
		try {
			conn = getConnection(companycode);
			if(conn != null){
				pstmt = conn.prepareStatement("{call dbo.udprpt_XFTotalByEmp(?,?,?)}"); 
				pstmt.setString(1, first); 
				pstmt.setString(2, last); 
				pstmt.setString(3, empno); 
				rs = pstmt.executeQuery(); 
				while (rs.next()) { 
					xfSum.setEmpno(rs.getString("empno"));
					xfSum.setEmpname(rs.getString("empname"));
					xfSum.setXfzaocannum(rs.getInt("xfzaocannum"));
					xfSum.setXfzaocan(rs.getFloat("xfzaocan"));
					xfSum.setZaocanjcnum(rs.getInt("zaocanjcnum"));
					xfSum.setXfzaocanbt(rs.getFloat("xfzaocanbt"));
					xfSum.setXfzaocankk(rs.getFloat("xfzaocankk"));
					
					xfSum.setXfwucannum(rs.getInt("xfwucannum"));
					xfSum.setXfwucan(rs.getFloat("xfwucan"));
					xfSum.setWucanjcnum(rs.getInt("wucanjcnum"));
					xfSum.setXfwucanbt(rs.getFloat("xfwucanbt"));
					xfSum.setXfwucankk(rs.getFloat("xfwucankk"));
					
					xfSum.setXfwancannum(rs.getInt("xfwancannum"));
					xfSum.setXfwancan(rs.getFloat("xfwancan"));
					xfSum.setWancanjcnum(rs.getInt("wancanjcnum"));
					xfSum.setXfwancanbt(rs.getFloat("xfwancanbt"));
					xfSum.setXfwancankk(rs.getFloat("xfwancankk"));
					
					xfSum.setXfyecannum(rs.getInt("xfyecannum"));
					xfSum.setXfyecan(rs.getFloat("xfyecan"));
					xfSum.setYecanjcnum(rs.getInt("yecanjcnum"));
					xfSum.setXfyecanbt(rs.getFloat("xfyecanbt"));
					xfSum.setXfyecankk(rs.getFloat("xfyecankk"));
					
					xfSum.setXflingcannum(rs.getInt("xflingcannum"));
					xfSum.setXflingcan(rs.getFloat("xflingcan"));
					xfSum.setLingcanjcnum(rs.getInt("lingcanjcnum"));
					xfSum.setXflingcanbt(rs.getFloat("xflingcanbt"));
					xfSum.setXflingcankk(rs.getFloat("xflingcankk"));

					xfSum.setChshimoneycount(rs.getFloat("chshimoneycount"));
					xfSum.setChshimoneycount2(rs.getFloat("chshimoneycount2"));
					xfSum.setCs(rs.getFloat("cs"));
					
					xfSum.setKf9990(rs.getFloat("kf9990"));
					xfSum.setKf9991(rs.getFloat("kf9991"));
					xfSum.setKf9992(rs.getFloat("kf9992"));
					xfSum.setKf(rs.getFloat("kf"));
					//早餐补贴标准
					xfSum.setZaocanbt1(rs.getFloat("zaocanbt1"));
					xfSum.setZaocanbt2(rs.getFloat("zaocanbt2"));
					xfSum.setZaocanbt3(rs.getFloat("zaocanbt3"));
					//午餐补贴标准
					xfSum.setWucanbt1(rs.getFloat("wucanbt1"));
					xfSum.setWucanbt2(rs.getFloat("wucanbt2"));
					xfSum.setWucanbt3(rs.getFloat("wucanbt3"));
					//晚餐补贴标准
					xfSum.setWancanbt1(rs.getFloat("wancanbt1"));
					xfSum.setWancanbt2(rs.getFloat("wancanbt2"));
					xfSum.setWancanbt3(rs.getFloat("wancanbt3"));
					//夜宵补贴标准，三地合并最高标准yecanbtBZ,
					xfSum.setYecanbt1(rs.getFloat("yecanbt1"));
					xfSum.setYecanbt2(rs.getFloat("yecanbt2"));
					xfSum.setYecanbt3(rs.getFloat("yecanbt3"));
					//凌晨补贴标准，三地合并最高标准lingcanbtBZ
					xfSum.setLingcanbt1(rs.getFloat("lingcanbt1"));
					xfSum.setLingcanbt2(rs.getFloat("lingcanbt2"));
					xfSum.setLingcanbt3(rs.getFloat("lingcanbt3"));
				} 
			}
			return xfSum;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public static void main(String args[]) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = format.parse("2018-12"+"-01");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		String first = format.format(calendar.getTime());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = format.format(calendar.getTime());
		System.out.println(first+" "+last);
	}
}
