package com.suntak.cloud.oa.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.cloud.oa.entity.Cux_oa_qywx_aqscfx_v;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.oa.mapper 
 * @Description: 安全责任书数据库操作
 * @date May 14, 2019 1:27:08 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface AqscfxMapper extends IMapper<Cux_oa_qywx_aqscfx_v> {

    /**
     * 更新推送标志
     * @param id
     * @return
     * @throws Exception      
     * @return: int      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @Update("update formson_6759 set field0019=1 where id = #{id}")
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
    @Update("update formson_6759 set field0018 = #{name} where id = #{id} and field0015 = #{empno}")
    public int signById(@Param("id") String id, @Param("empno") String empno, @Param("name") String name) throws Exception;
    
    @SelectProvider(type = SqlProvider.class, method = "findInfoByConditions")
    public List<Cux_oa_qywx_aqscfx_v> findInfoByConditions() throws Exception;
    
    class SqlProvider {
        public String findInfoByConditions(Map<String, Object> param){
            return new SQL(){
                {
                    SELECT("*");
                    FROM("cux_oa_qywx_aqscfx_v");
                    WHERE("l_txzt is null");
                }
            }.toString();
        }
    }
}
