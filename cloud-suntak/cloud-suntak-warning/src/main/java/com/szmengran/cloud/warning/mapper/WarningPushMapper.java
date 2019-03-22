package com.szmengran.cloud.warning.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.szmengran.cloud.warning.entity.T_warning_push;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.szmengran.cloud.warning.mapper 
 * @Description: 预警信息推送数据库操作
 * @date Mar 21, 2019 3:11:46 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface WarningPushMapper extends IMapper<T_warning_push> {
    
    /**
     * 查询预警信息
     * @param validstatus
     * @return
     * @throws Exception      
     * @return: List<T_warning_push>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @Select("select pushid,typeid,touser,msgtype,message from t_warning_push where validstatus=#{validstatus}")
    List<T_warning_push> searchWarningPush(@Param("validstatus") String validstatus) throws Exception;
    
    /**
     * 更新预警发送异常信息
     * @param exception
     * @param pushid
     * @return
     * @throws Exception      
     * @return: Boolean      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @Update("update t_warning_push set exception=#{exception},validstatus='0' where pushid=#{pushid}")
    int updateWarningPush(@Param("exception") String exception, @Param("pushid") Long pushid) throws Exception;
    
}
