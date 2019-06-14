package com.suntak.cloud.recruitment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.suntak.cloud.recruitment.entity.T_hr_workflow_sub;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.recruitment.mapper 
 * @Description: 子工作流
 * @date Jun 11, 2019 11:16:58 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface WorkflowSubMapper<T> extends IMapper<T> {
    
    /**
     * 查找工作流
     * @param preflowid
     * @param agree
     * @return
     * @throws Exception      
     * @return: List<T_hr_workflow_sub>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @Select("select * from t_hr_workflow_sub where preflowid=#{preflowid} and agree=#{agree}")
    List<T_hr_workflow_sub> findWorkflowSub(@Param("preflowid") Integer preflowid, @Param("agree") String agree) throws Exception;
}
