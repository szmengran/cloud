package com.suntak.cloud.ems.mapper;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.cloud.ems.entity.Ems_dm_equipment_details;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.ems.mapper 
 * @Description: 设备信息查询
 * @date Mar 26, 2019 4:09:32 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface EquipmentDetailsMapper extends IMapper<Ems_dm_equipment_details> {
    
    @SelectProvider(type = SqlProvider.class, method = "findEquipmentDetail")
    List<Ems_dm_equipment_details> findEquipmentDetail(@Param("useD") String useD, @Param("procedure") String procedure, 
            @Param("keyword") String keyword) throws Exception;
    
    class SqlProvider {
        public String findEquipmentDetail(Map<String, Object> params) {
            String useD = (String)params.get("useD");
            String procedure = (String)params.get("procedure");
            String keyword = (String)params.get("keyword");
            return new SQL() {
                {
                    SELECT("id,procedure,installation_location,use_d,equipment_name,equipment_alias,equipment_no,asset_id,manufacturer,start_time");
                    FROM("EMS_DM_EQUIPMENT_DETAILS");
                    if (StringUtils.isNotBlank(useD)) {
                        WHERE("use_d=#{useD}");
                    }
                    if (StringUtils.isNotBlank(procedure)) {
                        WHERE("procedure=#{procedure}");
                    }
                    if (StringUtils.isNotBlank(keyword)) {
                        WHERE("(equipment_no like '%"+keyword+"%' or asset_id like '%"+keyword+"%' or equipment_alias like '%"+keyword+"%' or equipment_name like '%"+keyword+"%')");
                    }
                }
            }.toString();
        }
    }
}
