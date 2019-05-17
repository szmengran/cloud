package com.suntak.cloud.ems.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.suntak.cloud.ems.entity.Ems_dm_repair_record;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.ems.mapper 
 * @Description: 维修记录数据库操作
 * @date Mar 18, 2019 10:45:20 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface RepairRecordMapper extends IMapper<Ems_dm_repair_record> {
    
    @Select("select HIBERNATE_SEQUENCE.nextval from dual")
    Long findSequence() throws Exception;
}
