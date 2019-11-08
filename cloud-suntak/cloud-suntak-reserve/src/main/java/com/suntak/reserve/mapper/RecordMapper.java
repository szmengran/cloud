package com.suntak.reserve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.suntak.reserve.entity.TReserveRecord;
import com.szmengran.mybatis.utils.mapper.IMapper;

@Mapper
public interface RecordMapper extends IMapper<TReserveRecord> {

	/**
	 * 
	 * @description 根据openid获取预约记录
	 * @param openid
	 * @return
	 * @date Nov 6, 2019 12:58:51 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Select("select * from t_reserve_record where openid = #{openid}")
	List<TReserveRecord> findRecordByOpenid(@Param("openid") String openid);
}
