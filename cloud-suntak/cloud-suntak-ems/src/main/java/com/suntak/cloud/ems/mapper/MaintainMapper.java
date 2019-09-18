package com.suntak.cloud.ems.mapper;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.cloud.ems.entity.Ems_dm_maintain;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.ems.mapper 
 * @Description: 保养记录数据库操作
 * @date Aug 23, 2019 1:47:14 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface MaintainMapper extends IMapper<Ems_dm_maintain> {
    
    @SelectProvider(type = SqlProvider.class, method = "findMaintain")
    List<Ems_dm_maintain> findMaintain(@Param("organization_id") Integer organization_id, @Param("keyword") String keyword, @Param("userid") Integer userid, @Param("id") Integer id) throws Exception;
    
    @Select("select id from ems_dm_maintain where equipment_id=#{equipment_id} and maintain_level=#{maintain_level} and status>#{status}")
    List<Ems_dm_maintain> findMaintainHistory(Ems_dm_maintain maintain) throws Exception;
    
    /**
     * 更新保养单的状态与执行人信息
     * @param maintain
     * @return
     * @throws Exception
     */
    @Update("update ems_dm_maintain set solo_person_id=#{solo_person_id},solo_person_name=#{solo_person_name},maintain_result=#{maintain_result},mlt_use_state=#{mlt_use_state},execute_time=#{execute_time},status=1 where id=#{id}")
    int updateMaintainStatus(Ems_dm_maintain maintain) throws Exception;
    
    class SqlProvider {
        public String findMaintain(Map<String, Object> params) {
            Integer organization_id = (Integer)params.get("organization_id");
            Integer userid = (Integer)params.get("userid");
            Integer id = (Integer)params.get("id");
            String keyword = (String)params.get("keyword");
            return new SQL() {
                {
                    SELECT("a.id,a.organization_id,a.equipment_id,c.equipment_alias,c.equipment_name,c.equipment_no,c.procedure,a.execute_time,a.status,a.maintain_level");
                    SELECT("decode(a.status,0,'待执行',1,'已执行',2,'审批中',3,'审核成功',4,'',-1,'审批异常','未知') status_name");
                    SELECT("decode(a.type,1,'设备','公共') typename,a.type,a.plan_date,before_img,after_img");
                    SELECT("(select wm_concat(b.person_name) from ems_md_person_details b where a.maintenance_area=b.area_person_id) area_person");
                    SELECT("a.maintain_person,a.solo_person_name,a.maintain_result,a.mlt_use_state");
                    FROM("ems_dm_maintain a,EMS_DM_EQUIPMENT_DETAILS c");
                    WHERE("a.equipment_id=c.id");
                    if (organization_id != null && organization_id != 0) {
                        WHERE("a.organization_id=#{organization_id}");
                    }
                    if (userid != null && userid != 0) {
                        WHERE("a.solo_person_id=#{userid}");
                    } else if (id != null && id != 0) {
                    	WHERE("a.maintenance_area in (" + 
                    			" SELECT distinct d.area_person_id FROM oz_org_userinfo e, OZ_SEC_USER f, ems_md_person_details d" + 
                    			" where e.login_name=f.login_name" + 
                    			" and d.person_id=f.id" + 
                    			" and e.id=#{id})");
                    }
                    if (StringUtils.isNotBlank(keyword)) {
                        WHERE("(c.equipment_name like '%"+keyword+"%' or c.equipment_alias like '%"+keyword+"%' or " + 
                                "c.equipment_no like '%"+keyword+"%' or " + 
                                "a.maintain_person like '%"+keyword+"%')");
                    }
                }
            }.toString();
        }
    }
}
