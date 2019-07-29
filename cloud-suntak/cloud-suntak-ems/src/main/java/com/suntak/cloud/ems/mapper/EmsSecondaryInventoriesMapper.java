package com.suntak.cloud.ems.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.suntak.cloud.ems.entity.Ems_secondary_inventories_v;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.ems.mapper 
 * @Description: TODO
 * @date Jul 23, 2019 2:25:06 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface EmsSecondaryInventoriesMapper extends IMapper<Ems_secondary_inventories_v> {

    /**
     * 查询来源库
     * @param organization_id
     * @return
     * @throws Exception      
     * @return: List<Ems_secondary_inventories_v>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @Select("select code, organization_id, description from ems_secondary_inventories_v where organization_id = #{organization_id} order by code asc")
    List<Ems_secondary_inventories_v> findInventories(@Param("organization_id") Integer organization_id) throws Exception;
}
