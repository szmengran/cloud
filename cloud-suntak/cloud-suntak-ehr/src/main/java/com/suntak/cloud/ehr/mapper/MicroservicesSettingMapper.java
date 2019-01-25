package com.suntak.cloud.ehr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.suntak.cloud.ehr.entity.T_microservices_setting;
import com.szmengran.mybatis.utils.mapper.IMapper;

/**
 * @Package com.suntak.cloud.ehr.mapper
 * @Description: 崇达小助手设置
 * @date Jan 24, 2019 11:25:14 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface MicroservicesSettingMapper extends IMapper<T_microservices_setting> {
	
	/**
	 * 根据工号查询设置信息
	 * @param empno
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Select("select empno,type from t_microservices_setting where empno=#{empno}")
	public List<T_microservices_setting> findSettingByEmpno(@Param("empno") String empno) throws Exception;
	
	/**
	 * 根据类型查询设置信息
	 * @param type
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Select("select empno from t_microservices_setting where type=#{type}")
	public List<T_microservices_setting> findSettingByType(@Param("type") String type) throws Exception;
}
