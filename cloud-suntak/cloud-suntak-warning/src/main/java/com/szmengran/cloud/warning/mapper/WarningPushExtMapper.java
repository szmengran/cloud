package com.szmengran.cloud.warning.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.szmengran.cloud.warning.entity.T_warning_push;
import com.szmengran.cloud.warning.entity.ext.T_warning_push_ext;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.szmengran.cloud.warning.mapper 
 * @Description: 预警信息推送数据库操作
 * @date Mar 21, 2019 3:11:46 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface WarningPushExtMapper extends IMapper<T_warning_push> {
    
    /**
     * 查询预警信息
     * @param validstatus
     * @return
     * @throws Exception      
     * @return: List<T_warning_push>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @SelectProvider(type = SqlProvider.class, method = "searchWarningPush")
    List<T_warning_push_ext> searchWarningPush(@Param("validstatus") String validstatus) throws Exception;
    
    class SqlProvider {
        public String searchWarningPush() {
            return new SQL() {
                {
                    SELECT("a.pushid,a.typeid,a.touser,a.msgtype,a.message,b.touser touser2,b.topart,b.totag");
                    FROM("t_warning_push a");
                    LEFT_OUTER_JOIN("t_warning_type b on a.typeid = b.typeid");
                    WHERE("a.validstatus = #{validstatus}");
                }
            }.toString();
        }
    }
}
