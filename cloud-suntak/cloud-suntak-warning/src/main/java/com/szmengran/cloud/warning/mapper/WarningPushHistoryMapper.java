package com.szmengran.cloud.warning.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.szmengran.cloud.warning.entity.T_warning_push_history;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.szmengran.cloud.warning.mapper 
 * @Description: 预警信息推送历史数据库操作
 * @date Mar 21, 2019 3:30:26 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface WarningPushHistoryMapper extends IMapper<T_warning_push_history> {

    @InsertProvider(type = SqlProvider.class, method = "insertInto")
    int insertInto(@Param("pushid") Long pushid) throws Exception;
    
    class SqlProvider {
        public String insertInto() {
            StringBuilder sql = new StringBuilder();
            sql.append("insert into t_warning_push_history(")
               .append(" PUSHID,TYPEID,TOUSER,MSGTYPE,ORGID,WIPID,MESSAGE,EXCEPTION,ATTRIBUTE1,ATTRIBUTE2,")
               .append(" ATTRIBUTE3,CREATESTAMP,UPDATESTAMP,VALIDSTATUS)")
               .append(" SELECT PUSHID,TYPEID,TOUSER,MSGTYPE,ORGID,WIPID,MESSAGE,EXCEPTION,ATTRIBUTE1,ATTRIBUTE2,")
               .append(" ATTRIBUTE3,CREATESTAMP,sysdate,VALIDSTATUS")
               .append(" from t_warning_push ")
               .append(" WHERE PUSHID=#{pushid}");
            return sql.toString();
        }
    }
}
