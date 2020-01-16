package com.suntak.push.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.suntak.push.entity.CuxSoaMiPush;
import com.suntak.push.entity.TPushRobot;
import com.suntak.push.entity.ext.CuxSoaMiPushExt;
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
	 * @param cuxSoaMiPushExt
	 * @return
	 * @date Sep 27, 2019 1:53:20 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
//	@Update("update cux.cux_soa_mi_push set push_mark=#{push_mark},push_date=#{push_date},attribute30=#{attribute30},last_update_date=#{last_update_date} where push_date is null and stop_time between #{range_start} and #{range_end} and mi_status=#{mi_status} and organization_id=#{organization_id}")
//	int updatePush(CuxSoaMiPushExt cuxSoaMiPushExt);
	
	/**
	 * 
	 * @description 查找所有需要推送数据的机器人
	 * @return
	 * @date Sep 27, 2019 1:53:11 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
//	@Select("select distinct b.* from cux.cux_soa_mi_push a, t_push_robot b where a.mi_status=b.name and b.type='MI' and a.stop_time between b.range_start and b.range_end and a.organization_id = b.org_id and b.validstatus=1 and a.push_date is null")
//	List<TPushRobot> findRobot();
	
	/**
	 * 
	 * @description 查找普通件所有需要推送数据的机器人
	 * 1.普通件
	 * 2.交期在30天之内
	 * 3.停留时间大于24小时
	 * @return
	 * @date Jan 13, 2020 1:28:57 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Select("select distinct b.* from cux.cux_soa_mi_push a, t_push_robot b where a.mi_status=b.name and b.type='MI' and a.organization_id = b.org_id and b.validstatus=1 and a.attribute1 = '普通件' and a.sch_date < sysdate + 30 and a.stop_time > 24 and a.push_date is null")
	List<TPushRobot> findCommonRobot();
	
	/**
	 * 
	 * @description 更新普通件推送状态
	 * @param cuxSoaMiPushExt
	 * @return
	 * @date Sep 27, 2019 1:53:20 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Update("update cux.cux_soa_mi_push set push_mark=#{push_mark},push_date=#{push_date},attribute30=#{attribute30},last_update_date=#{last_update_date} where push_date is null and attribute1 = '普通件' and sch_date < sysdate + 30 and stop_time > 24 and mi_status=#{mi_status} and organization_id=#{organization_id}")
	int updateCommonPush(CuxSoaMiPushExt cuxSoaMiPushExt);
	
	/**
	 * 
	 * @description 查找快件，收费保期所有需要推送数据的机器人
	 * 1.快件，收费保期
	 * 2.停留时间大于12小时
	 * @return
	 * @date Jan 13, 2020 1:28:57 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Select("select distinct b.* from cux.cux_soa_mi_push a, t_push_robot b where a.mi_status=b.name and b.type='MI' and a.organization_id = b.org_id and b.validstatus=1 and a.attribute1 in ('快件','收费保期') and a.stop_time > 12 and a.push_date is null")
	List<TPushRobot> findSpecialRobot();
	
	/**
	 * 
	 * @description 更新普通件推送状态
	 * @param cuxSoaMiPushExt
	 * @return
	 * @date Sep 27, 2019 1:53:20 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Update("update cux.cux_soa_mi_push set push_mark=#{push_mark},push_date=#{push_date},attribute30=#{attribute30},last_update_date=#{last_update_date} where push_date is null and attribute1 in ('快件','收费保期') and stop_time > 12 and mi_status=#{mi_status} and organization_id=#{organization_id}")
	int updateSpecialPush(CuxSoaMiPushExt cuxSoaMiPushExt);
}
