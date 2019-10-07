package com.suntak.push.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.suntak.push.entity.CuxSoaMiPush;
import com.suntak.push.entity.TPushRobot;
import com.szmengran.mybatis.utils.mapper.IMapper;

@Mapper
public interface CuxSoaMiPushMapper extends IMapper<CuxSoaMiPush> {

	/**
	 * 
	 * @description 根据序列号查找推送的列表信息
	 * @param attribute30
	 * @return
	 * @date Sep 27, 2019 1:53:29 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Select("select * from cux.cux_soa_mi_push where attribute30=#{attribute30}")
	List<CuxSoaMiPush> findBySeq(String attribute30);
	
	/**
	 * 
	 * @description 更新推送状态
	 * @param cuxSoaMiPush
	 * @return
	 * @date Sep 27, 2019 1:53:20 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Update("update cux.cux_soa_mi_push set push_mark=#{push_mark},push_date=#{push_date},attribute30=#{attribute30},last_update_date=#{last_update_date} where push_date is null and mi_status=#{mi_status}")
	int updatePush(CuxSoaMiPush cuxSoaMiPush);
	
	/**
	 * 
	 * @description 查找所有需要推送数据的机器人
	 * @return
	 * @date Sep 27, 2019 1:53:11 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Select("select distinct a.organization_id,b.* from cux.cux_soa_mi_push a, t_push_robot b where a.mi_status=b.name and b.type='MI' and a.stop_time between b.range_start and b.range_end and b.validstatus=1 and a.push_date is null")
	List<TPushRobot> findRobot();
}
