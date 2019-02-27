package com.suntak.cloud.oa.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.cloud.oa.entity.Cux_oa_qywx_grlhzhpj_v;
import com.szmengran.mybatis.utils.mapper.IMapper;

/**
 * @Package com.suntak.cloud.oa.mapper
 * @Description: 技能工资明细表
 * @date Jan 28, 2019 9:14:26 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface GrlhzhpjMapper extends IMapper<Cux_oa_qywx_grlhzhpj_v> {

	@Update("update formson_5766 set field0093=1 where id = #{id}")
	public int updateById(@Param("id") String id) throws Exception;
	
	@Update("update formson_5766 set field0095 = #{name} where id = #{id} and field0081 = #{empno}")
	public int signById(@Param("id") String id, @Param("empno") String empno, @Param("name") String name) throws Exception;
	
	@SelectProvider(type = SqlProvider.class, method = "findInfoByConditions")
	public List<Cux_oa_qywx_grlhzhpj_v> findInfoByConditions() throws Exception;
	
	class SqlProvider {
		public String findInfoByConditions(Map<String, Object> param){
	        return new SQL(){
	            {
	                SELECT("*");
	                FROM("cux_oa_qywx_grlhzhpj_v");
	                WHERE("l_txzt is null");
	            }
	        }.toString();
	    }
	}
}
