package com.suntak.cloud.ems.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.cloud.ems.entity.Ems_dm_maintain_content;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.ems.mapper 
 * @Description: 保养内容明细数据库操作
 * @date Aug 23, 2019 3:15:21 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface MaintainContentMapper extends IMapper<Ems_dm_maintain_content> {

    @SelectProvider(type = SqlProvider.class, method = "findMaintainContent")
    List<Ems_dm_maintain_content> findMaintainContent(@Param("maintain_id") Long maintain_id) throws Exception;
    
    @SelectProvider(type = SqlProvider.class, method = "findMaintainContent")
    List<Ems_dm_maintain_content> findMaintainContentHistory(@Param("equipment_id") Long equipment_id, @Param("maintain_level") String maintain_level, @Param("status") String status) throws Exception;
    
    @Update("update ems_dm_maintain_content set m_check=#{m_check}," + 
            "m_clear=#{m_clear}," + 
            "m_adjust=#{m_adjust}," + 
            "m_lubric=#{m_lubric}," + 
            "m_repair=#{m_repair}," + 
            "m_change=#{m_change}," + 
            "p_check=#{p_check}," + 
            "p_clear=#{p_clear}," + 
            "p_change=#{p_change}," + 
            "p_gasup=#{p_gasup}," + 
            "p_correction=#{p_correction}," + 
            "p_fastening=#{p_fastening}," + 
            "p_dredge=#{p_dredge}," + 
            "p_temperature=#{p_temperature},index_=#{index_} where id=#{id}")
    int updateById(Ems_dm_maintain_content Ems_dm_maintain_content) throws Exception;
    
    class SqlProvider {
        public String findMaintainContent(Map<String, Object> map) {
            return new SQL() {
                {
                    SELECT("maintain_id,index_,id,content,m_check,m_clear,m_adjust,m_lubric,m_repair");
                    SELECT("m_change,exe_detail,p_check,p_clear,p_change,p_gasup,p_correction,");
                    SELECT("p_fastening,p_dredge,p_temperature");
                    FROM("ems_dm_maintain_content");
                    WHERE("maintain_id=#{maintain_id}");
                }
            }.toString();
        }
        public String findMaintainContentHistory(Map<String, Object> map) {
            return new SQL() {
                {
                    SELECT("maintain_id,index_,id,content,m_check,m_clear,m_adjust,m_lubric,m_repair");
                    SELECT("m_change,exe_detail,p_check,p_clear,p_change,p_gasup,p_correction,");
                    SELECT("p_fastening,p_dredge,p_temperature");
                    FROM("ems_dm_maintain_content");
                    WHERE("equipment_id=#{equipment_id}");
                    WHERE("maintain_level=#{maintain_level}");
                    WHERE("status=#{status}");
                }
            }.toString();
        }
    }
}
