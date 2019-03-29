package com.suntak.cloud.oa.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.cloud.oa.entity.Cux_oa_qywx_jngzmx_v;
import com.szmengran.mybatis.utils.mapper.IMapper;

/**
 * @Package com.suntak.cloud.oa.mapper
 * @Description: 团队量化综合评价表
 * @date Jan 28, 2019 9:14:26 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface JngzmxMapper extends IMapper<Cux_oa_qywx_jngzmx_v> {

    /**
     * 更新推送标志
     * @param id
     * @return
     * @throws Exception      
     * @return: int      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
	@Update("update formson_6176 set field0027=1 where id = #{id}")
	public int updateById(@Param("id") String id) throws Exception;
	
	 /**
     * 员工签名
     * @param id
     * @param empno
     * @param name
     * @return
     * @throws Exception      
     * @return: int      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
	@Update("update formson_6176 set field0025 = #{name} where id = #{id} and field0012 = #{empno}")
	public int signById(@Param("id") String id, @Param("empno") String empno, @Param("name") String name) throws Exception;
	
	@SelectProvider(type = SqlProvider.class, method = "findInfoByConditions")
	public List<Cux_oa_qywx_jngzmx_v> findInfoByConditions() throws Exception;
	
	class SqlProvider {
		public String findInfoByConditions(Map<String, Object> param){
	        return new SQL(){
	            {
	                SELECT("*");
	                FROM("cux_oa_qywx_jngzmx_v");
	                WHERE("l_txzt is null");
	            }
	        }.toString();
	    }
	}
}
