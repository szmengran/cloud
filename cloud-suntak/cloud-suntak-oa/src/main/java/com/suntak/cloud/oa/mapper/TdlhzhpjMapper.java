package com.suntak.cloud.oa.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.cloud.oa.entity.Cux_oa_qywx_jngzmx_v;
import com.suntak.cloud.oa.entity.Cux_oa_qywx_tdlhzhpj_v;
import com.szmengran.mybatis.utils.mapper.IMapper;

/**
 * @Package com.suntak.cloud.oa.mapper
 * @Description: 个人量化综合评价表
 * @date Jan 28, 2019 9:14:26 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface TdlhzhpjMapper extends IMapper<Cux_oa_qywx_tdlhzhpj_v> {

	@Update("update formson_5765 set field0092=1 where id = #{id}")
	public int updateById(@Param("id") String id) throws Exception;
	
	@Update("update formson_5765 set field0094 = #{name} where id = #{id} and field0061 = #{empno}")
	public int signById(@Param("id") String id, @Param("empno") String empno, @Param("name") String name) throws Exception;
	
	@SelectProvider(type = SqlProvider.class, method = "findInfoByConditions")
	public List<Cux_oa_qywx_jngzmx_v> findInfoByConditions() throws Exception;
	
	class SqlProvider {
		public String findInfoByConditions(Map<String, Object> param){
	        return new SQL(){
	            {
	                SELECT("*");
	                FROM("cux_oa_qywx_tdlhzhpj_v");
	                WHERE("l_txzt is null");
	            }
	        }.toString();
	    }
	}
}
