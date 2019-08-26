package com.suntak.cloud.ems.mapper;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.cloud.ems.entity.Ems_user_v;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.ems.mapper 
 * @Description: EBS用户数据库操作
 * @date Jul 30, 2019 2:35:36 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface EmsUserVMapper extends IMapper<Ems_user_v> {
    
    /**
     * 根据用户名查找用户
     * @param user_name
     * @return
     * @throws Exception      
     * @return: List<Ems_user_v>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @Select("select * from ems_user_v where user_name=#{user_name}")
    List<Ems_user_v> findByUsername(@Param("user_name") String user_name) throws Exception;
    
    /**
     * 根据关键字查找用户
     * @param keyword
     * @return
     * @throws Exception      
     * @return: List<Ems_user_v>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @SelectProvider(type = SqlProvider.class, method = "findUser")
    List<Ems_user_v> findUser(@Param("keyword") String keyword) throws Exception;
    
    class SqlProvider {
        public String findUser(Map<String, Object> map) throws Exception{
            String keyword = (String)map.get("keyword");
            return new SQL() {
                {
                    SELECT("*");
                    FROM("ems_user_v");
                    if (StringUtils.isNotBlank(keyword)) {
                        WHERE("description like '%"+keyword+"%' or employee_name like '%"+keyword+"%' or user_name like '%"+keyword+"%'");
                    }
                }
            }.toString();
        }
    }
}
