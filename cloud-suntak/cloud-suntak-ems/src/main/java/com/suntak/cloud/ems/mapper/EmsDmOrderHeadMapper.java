package com.suntak.cloud.ems.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.suntak.cloud.ems.entity.Ems_dm_order_head;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.ems.mapper 
 * @Description: TODO
 * @date Jul 23, 2019 8:21:03 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface EmsDmOrderHeadMapper extends IMapper<Ems_dm_order_head> {

    /**
     * 查询备件出库领用记录
     * @param use_p_id
     * @return
     * @throws Exception      
     * @return: List<Ems_dm_order_head>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @Select("select * from ems_dm_order_head where use_p_id = #{use_p_id}")
    List<Ems_dm_order_head> findOrders(@Param("use_p_id") Integer use_p_id) throws Exception;
    
    /**
     * 查找序列号
     * @return
     * @throws Exception      
     * @return: Integer      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @Select("SELECT HIBERNATE_SEQUENCE.Nextval from dual")
    Integer findSeq() throws Exception;
}
