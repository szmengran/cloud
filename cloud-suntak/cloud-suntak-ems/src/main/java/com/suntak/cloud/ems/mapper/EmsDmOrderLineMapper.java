package com.suntak.cloud.ems.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.suntak.cloud.ems.entity.Ems_dm_order_line;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.ems.mapper 
 * @Description: TODO
 * @date Jul 23, 2019 8:21:03 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface EmsDmOrderLineMapper extends IMapper<Ems_dm_order_line> {
    
//    @Insert({
//        "<script>",
//        "insert all",
//        "<foreach collection='ems_dm_order_lines' item='item'>",
//        "into ems_dm_order_line(ebs_user_name,onhand_qty,id,part_id,price,type,line_id,use_d,item_type,subinventory_code,item_refer,cost_type,equipment_no,part_name,uom_code,equipment_id,head_id,qty,organization_id,distribution_account,actual_qty,subinventory_name,part_no,line_type_id,ebs_user_id,item_id,amount,use_dname,equipment_name) values",
//        "(#{item.ebs_user_name},#{item.onhand_qty},HIBERNATE_SEQUENCE.NEXTVAL,#{item.part_id},#{item.price},#{item.type},#{item.line_id},#{item.use_d},#{item.item_type},#{item.subinventory_code},#{item.item_refer},#{item.cost_type},#{item.equipment_no},#{item.part_name},#{item.uom_code},#{item.equipment_id},#{item.head_id},#{item.qty},#{item.organization_id},#{item.distribution_account},#{item.actual_qty},#{item.subinventory_name},#{item.part_no},#{item.line_type_id},#{item.ebs_user_id},#{item.item_id},#{item.amount},#{item.use_dname},#{item.equipment_name})",
//        "</foreach>",
//        "select 1 from dual",
//        "</script>"
//    })
//    int saveBatch(@Param(value="ems_dm_order_lines") List<Ems_dm_order_line> ems_dm_order_lines) throws Exception;
}
