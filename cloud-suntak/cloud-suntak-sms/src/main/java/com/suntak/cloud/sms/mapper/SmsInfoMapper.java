package com.suntak.cloud.sms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.suntak.cloud.sms.entity.T_sms_info;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.sms.mapper 
 * @Description: 短信数据库操作API
 * @date Mar 5, 2019 4:56:35 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface SmsInfoMapper extends IMapper<T_sms_info>{

    /**
     * 将发送的短信异常信息更新到表中
     * @param id
     * @param exception
     * @return
     * @throws Exception      
     * @return: int      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
	@Update("update t_sms_info set validstatus=0, exception=#{exception} where id=#{id}")
	int updateException(@Param("id") Integer id, @Param("exception") String exception) throws Exception;
	
	/**
	 * 查找小于当前日期的短信进行发送
	 * @return
	 * @throws Exception      
	 * @return: List<T_sms_info>      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Select("select * from t_sms_info where validstatus=1 and autosendtime < sysdate")
	List<T_sms_info> findAutoSendSms() throws Exception;
	
	/**
	 * 将发送成功的短信会写到历史表中
	 * @param id
	 * @return
	 * @throws Exception      
	 * @return: int      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@InsertProvider(type = SqlProvider.class, method = "insertIntoHistory")
	int insertIntoHistory(@Param("id") Integer id) throws Exception;
	    
	class SqlProvider {
	    public String insertIntoHistory() {
	        StringBuilder sql = new StringBuilder();
	        sql.append("insert into t_sms_info_history select * from t_sms_info")
	           .append(" WHERE id=#{id}");
	       return sql.toString();
	   }
	}
}
