package com.suntak.cloud.oa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.suntak.cloud.oa.entity.Cux_oa_personal_major_plan_v;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.oa.mapper 
 * @Description: 职业资格数据操作
 * @date Mar 28, 2019 4:04:45 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface PersonalMajorPlanMapper extends IMapper<Cux_oa_personal_major_plan_v> {
    
    /**
     * 根据主表ID查询职业资格数据
     * @param main_form_id
     * @return
     * @throws Exception      
     * @return: List<Cux_oa_personal_major_plan_v>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @Select("select * from cux_oa_personal_major_plan_v where main_form_id=#{main_form_id} order by sort_no")
    public List<Cux_oa_personal_major_plan_v> findInfoByConditions(@Param("main_form_id") String main_form_id) throws Exception;
}
