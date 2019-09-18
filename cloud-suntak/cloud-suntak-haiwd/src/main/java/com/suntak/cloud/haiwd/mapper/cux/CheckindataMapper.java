package com.suntak.cloud.haiwd.mapper.cux;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.suntak.cloud.haiwd.entity.T_haiwd_checkindata;
import com.szmengran.mybatis.utils.mapper.IMapper;

public interface CheckindataMapper extends IMapper<T_haiwd_checkindata> {
	
	/**
	 * 更新打卡记录同步状态
	 * @param userid
	 * @param checkin_time
	 * @return
	 */
	@Update("update t_haiwd_checkindata set status=0 where userid=#{userid} and checkin_time=#{checkin_time}")
	int updateStatus(@Param("userid") String userid, @Param("checkin_time") Long checkin_time);
}
