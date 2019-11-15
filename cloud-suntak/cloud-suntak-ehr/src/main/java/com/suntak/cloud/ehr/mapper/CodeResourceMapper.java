package com.suntak.cloud.ehr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.suntak.cloud.ehr.entity.CodeResource;
import com.szmengran.mybatis.utils.mapper.IMapper;

@Mapper
public interface CodeResourceMapper extends IMapper<CodeResource> {

	/**
	 * 
	 * @description 根据type code查找配置信息
	 * @param c_type_code
	 * @return
	 * @date Nov 14, 2019 8:35:27 AM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Select("select c_code,c_value from tp_v_code_resource_cn where c_type_code = #{c_type_code}")
	List<CodeResource> findResource(@Param("c_type_code") String c_type_code);
}
