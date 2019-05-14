package com.suntak.cloud.oa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.suntak.cloud.oa.entity.Cux_oa_personal_dev_plan_v;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.oa.mapper 
 * @Description: 个人学历及资格提升主表
 * @date Mar 28, 2019 4:02:07 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface PersonalDevPlanMapper extends IMapper<Cux_oa_personal_dev_plan_v> {
    
    /**
     * 更新推送标志
     * @param id
     * @return
     * @throws Exception      
     * @return: int      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @Update("update formmain_6388 set field0036=1 where id = #{id}")
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
    @Update("update formmain_6388 set field0037 = #{name} where id = #{id} and field0004 = #{empno}")
    public int signById(@Param("id") String id, @Param("empno") String empno, @Param("name") String name) throws Exception;
    
    /**
     * 查询个人学历及资格提升数据
     * @return
     * @throws Exception      
     * @return: List<Cux_oa_personal_dev_plan_v>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @Select("select * from cux_oa_personal_dev_plan_v where is_send_msg is null")
    public List<Cux_oa_personal_dev_plan_v> findInfoByConditions() throws Exception;
}
