package com.suntak.cloud.microservices.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.suntak.cloud.microservices.entity.Signcard;
import com.szmengran.mybatis.utils.mapper.IMapper;

@Mapper
public interface SigncardMapper extends IMapper<Signcard> {
	
	/**
	 * 
	 * @description 更新签卡状态
	 * @param signcard
	 * @return
	 * @date Jan 21, 2020 11:14:31 AM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Update("update signcard set validstatus=0 where empcode=#{empcode} and yearmonth=#{yearmonth} and validstatus=#{validstatus}")
	int updateStatus(Signcard signcard);
}
