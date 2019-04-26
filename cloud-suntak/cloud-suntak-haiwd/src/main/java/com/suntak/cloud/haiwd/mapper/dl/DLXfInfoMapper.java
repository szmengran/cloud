package com.suntak.cloud.haiwd.mapper.dl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.suntak.punch.entity.Cux_xf_info;
import com.szmengran.mybatis.utils.mapper.IMapper;

/**
 * @Package com.suntak.cloud.microservices.payment.mapper
 * @Description: 消费记录查询
 * @date Dec 24, 2018 2:12:21 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface DLXfInfoMapper extends IMapper<Cux_xf_info>{
	
	@SelectProvider(type = SqlProvider.class, method = "findSTXFByConditions")
	public List<Cux_xf_info> findSTXFByConditions(@Param("empno") String empno, @Param("companycode") String companycode) throws Exception;
	
	
	@SelectProvider(type = SqlProvider.class, method = "findCSXFByConditions")
	public List<Cux_xf_info> findCSXFByConditions(@Param("empno") String empno, @Param("companycode") String companycode) throws Exception;
	
	class SqlProvider {
		public String findSTXFByConditions(Map<String, Object> param){
			StringBuilder strSql = new StringBuilder();
			strSql.append("select xfposday,xfposmoney,devid,mealtypeid,xfcardmoney")
			      .append(" from cux_xf_info")
			      .append(" where empno = #{empno} order by xfposday desc");
			
			return strSql.toString();
	    }
		
		public String findCSXFByConditions(Map<String, Object> param){
			StringBuilder strSql = new StringBuilder();
			strSql.append("select xfposday,xfposmoney,devid,mealtypeid,xfcardmoney")
				  .append(" from cux_xf_touzhi_info")
			      .append(" where empno = #{empno} order by xfposday desc");
			return strSql.toString();
		}
	}
}
