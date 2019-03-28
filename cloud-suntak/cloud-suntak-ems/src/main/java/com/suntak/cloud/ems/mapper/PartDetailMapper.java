package com.suntak.cloud.ems.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.cloud.ems.entity.Ems_partdetail_v;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.ems.mapper 
 * @Description: 配件信息数据库操作
 * @date Mar 19, 2019 11:30:55 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface PartDetailMapper extends IMapper<Ems_partdetail_v> {
    
    /**
     * 查找配件信息
     * @param keyword 
     * @param org_id
     * @return
     * @throws Exception      
     * @return: List<Ems_partdetail_v>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @SelectProvider(type = SqlProvider.class, method = "findPartDetailInfo")
    List<Ems_partdetail_v> findPartDetailInfo(@Param("keyword") String keyword, @Param("org_id") Integer org_id) throws Exception;
    
    class SqlProvider {
        public String findPartDetailInfo(Map<String, Object> params) {
            String keyword = (String)params.get("keyword");
            return new SQL() {
                {
                    SELECT("id, organization_id, organization_code, item_id, part_no, part_name, uom_code, price, onhand_qty, onroad_qty,total_onhand_qty");
                    FROM("ems_partdetail_v");
                    WHERE("(part_name like '%"+keyword+"%' or part_no like '%"+keyword+"%' )");
                    WHERE("organization_id = #{org_id}");
                }
            }.toString();
        }
    }
}
