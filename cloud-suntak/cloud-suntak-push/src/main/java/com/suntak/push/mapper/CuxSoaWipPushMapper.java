package com.suntak.push.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.suntak.push.entity.CuxSoaWipPush;
import com.suntak.push.entity.TPushRobot;
import com.szmengran.mybatis.utils.mapper.IMapper;

@Mapper
public interface CuxSoaWipPushMapper extends IMapper<CuxSoaWipPush> {

	/**
	 * 
	 * @description 根据序列号查找推送的列表信息
	 * @param attribute30
	 * @return
	 * @date Sep 27, 2019 1:54:08 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Select("select * from cux.cux_soa_wip_push where attribute30=#{attribute30}")
	List<CuxSoaWipPush> queryBySeq(String attribute30);
	
	/**
	 * 
	 * @description 更新推送状态
	 * @param cuxSoaWipPush
	 * @return
	 * @date Sep 27, 2019 1:54:00 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Update("update cux.cux_soa_wip_push set push_mark='Y',push_date=#{push_date},attribute30=#{attribute30},last_update_date=#{last_update_date} where push_date is null and department_name=#{department_name}")
	int updatePush(CuxSoaWipPush cuxSoaWipPush);
	
	/**
	 * 
	 * @description 查找所有需要推送数据的机器人
	 * @return
	 * @date Sep 27, 2019 1:53:11 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Select("select distinct b.* from cux.cux_soa_wip_push a, t_push_robot b where a.department_name=b.name and b.type='WIP' and a.push_date is null")
	List<TPushRobot> findRobot();
}
