package com.suntak.cloud.ems.mapper;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.cloud.ems.entity.Ems_dm_maintain;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.ems.mapper 
 * @Description: 保养记录数据库操作
 * @date Aug 23, 2019 1:47:14 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface MaintainMapper extends IMapper<Ems_dm_maintain> {
    
    @SelectProvider(type = SqlProvider.class, method = "findMaintain")
    List<Ems_dm_maintain> findMaintain(@Param("organization_id") Integer organization_id, @Param("keyword") String keyword) throws Exception;
    
    class SqlProvider {
        public String findMaintain(Map<String, Object> params) {
            Integer organization_id = (Integer)params.get("organization_id");
            String keyword = (String)params.get("keyword");
            return new SQL() {
                {
                    SELECT("a.id,a.organization_id,c.equipment_alias,c.equipment_name,c.equipment_no,c.procedure,a.execute_time,a.status,a.maintain_level");
                    SELECT("decode(a.status,0,'待执行',1,'已执行',2,'审批中',3,'审核成功',4,'',-1,'审批异常','未知') status_name");
                    SELECT("decode(a.type,1,'设备','公共') typename,a.plan_date,before_img,after_img,execute_time");
                    SELECT("(select wm_concat(b.person_name) from ems_md_person_details b where a.maintenance_area=b.area_person_id) area_person");
                    SELECT("a.maintain_person,a.solo_person_name,a.maintain_result,a.mlt_use_state");
                    FROM("ems_dm_maintain a,EMS_DM_EQUIPMENT_DETAILS c");
                    WHERE("a.equipment_id=c.id");
                    if (organization_id != null && organization_id != 0) {
                        WHERE("a.organization_id=#{organization_id}");
                    }
                    if (StringUtils.isBlank(keyword)) {
                        WHERE("(equipmentd1_.equipment_name like '%"+keyword+"%' or equipmentd1_.equipment_alias like '%"+keyword+"%' or " + 
                                "equipmentd1_.equipment_no like '%"+keyword+"%' or " + 
                                "maintain0_.maintain_person like '%"+keyword+"%')");
                    }
                }
            }.toString();
        }
    }
}
