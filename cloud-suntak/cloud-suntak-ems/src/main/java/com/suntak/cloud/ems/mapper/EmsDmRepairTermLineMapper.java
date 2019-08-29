package com.suntak.cloud.ems.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.cloud.ems.entity.Ems_dm_repair_term_line;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.ems.mapper 
 * @Description: 保养单行信息管理
 * @date Aug 26, 2019 8:22:20 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface EmsDmRepairTermLineMapper extends IMapper<Ems_dm_repair_term_line> {
    
    /**
     * 查找保养单行信息
     * @param equipment_name
     * @param maintenance_level
     * @return
     * @throws Exception      
     * @return: List<Ems_dm_repair_term_line>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @SelectProvider(type = SqlProvider.class, method = "findRepairTermLine")
    List<Ems_dm_repair_term_line> findRepairTermLine(@Param("equipment_name") String equipment_name, @Param("maintenance_level") String maintenance_level) throws Exception;
    
    class SqlProvider {
        public String findRepairTermLine(Map<String, Object> map) {
            return new SQL() {
                {
                    SELECT("a.*");
                    FROM("EMS_DM_REPAIR_TERM_line a, EMS_DM_REPAIR_TERM_HEAD b");
                    WHERE("a.head_id=b.id");
                    WHERE("b.equipment_name=#{equipment_name}");
                    WHERE("b.maintenance_leve=#{maintenance_level}");
                }
            }.toString();
        }
    }
}
