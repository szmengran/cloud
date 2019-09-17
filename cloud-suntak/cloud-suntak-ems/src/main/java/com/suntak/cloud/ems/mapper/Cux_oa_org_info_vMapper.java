package com.suntak.cloud.ems.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.suntak.cloud.ems.entity.Cux_oa_org_info_v;
import com.szmengran.mybatis.utils.mapper.IMapper;

@Mapper
public interface Cux_oa_org_info_vMapper extends IMapper<Cux_oa_org_info_v> {
	
	@Select("select * from cux_oa_org_info_v@to_prod where org_id=#{org_id}")
	List<Cux_oa_org_info_v> findByOrg_id(Integer org_id);
}
