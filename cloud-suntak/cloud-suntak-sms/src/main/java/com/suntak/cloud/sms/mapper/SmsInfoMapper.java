package com.suntak.cloud.sms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

	@Update("update t_sms_info set validstatus=#{validstatus} where id=#{id}")
	int updateStatus(@Param("id") Integer id, @Param("validstatus") String validstatus) throws Exception;
	
}
