package com.suntak.cloud.ems.mapper;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.cloud.ems.entity.ext.Ems_dm_repair_record_ext;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.ems.mapper 
 * @Description: 维修记录数据库操作
 * @date Mar 18, 2019 10:45:20 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface RepairRecordExtMapper extends IMapper<Ems_dm_repair_record_ext> {
    
    @SelectProvider(type = SqlProvider.class, method = "findRepairRecord")
    List<Ems_dm_repair_record_ext> findRepairRecord(@Param("userid") Integer userid, @Param("keyword") String keyword) throws Exception;
    
    class SqlProvider {
        public String findRepairRecord(Map<String, Object> params) throws Exception {
            String keyword = (String)params.get("keyword");
            return new SQL() {
                {
                    SELECT("a.*,b.equipment_alias,b.equipment_no,b.use_d,b.procedure,b.equipment_name");
                    FROM("EMS_DM_REPAIR_RECORD a left join EMS_DM_EQUIPMENT_DETAILS b on a.equipment_id=b.id");
                    WHERE("a.maintenance_apllicant_id=#{userid}");
                    if (StringUtils.isNotBlank(keyword)) {
                        WHERE("(b.equipment_no like '%"+keyword+"%' or b.equipment_alias like '%"+keyword+"%' or b.equipment_name like '%"+keyword+"%')");
                    }
                }
            }.toString();
        }
    }
}
