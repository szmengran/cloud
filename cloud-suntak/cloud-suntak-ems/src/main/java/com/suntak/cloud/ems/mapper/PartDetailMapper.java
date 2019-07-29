package com.suntak.cloud.ems.mapper;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
    
    @SelectProvider(type = SqlProvider.class, method = "findPartByNo")
    List<Ems_partdetail_v> findPartByNo(@Param("part_no") String part_no, @Param("org_id") Integer org_id) throws Exception;
    
    class SqlProvider {
        public String findPartDetailInfo(Map<String, Object> params) {
            String keyword = (String)params.get("keyword");
            Integer org_id = (Integer)params.get("org_id");
            return new SQL() {
                {
                    SELECT("id, organization_id, organization_code, organization_name, item_id, part_no, part_name, uom_code, primary_uom_code, part_type, price, category1, category2, onhand_qty, onroad_qty, last_buy_date, last_buy_amount, total_onhand_qty, system_flag");
                    FROM("ems_partdetail_v");
                    WHERE("system_flag = 'Y'");
                    if (StringUtils.isNotBlank(keyword)) {
                        if (keyword.length() == 9) {
                            WHERE("part_no = #{keyword}");
                        } else {
                            WHERE("(part_name like '%"+keyword+"%' or part_no like '%"+keyword+"%' )");
                        }
                    }
                    if (org_id != null && org_id != 0) {
                        WHERE("organization_id = #{org_id}");
                    }
                }
            }.toString();
        }
        
        public String findPartByNo(Map<String, Object> params) {
            String part_no = (String)params.get("part_no");
            Integer org_id = (Integer)params.get("org_id");
            return new SQL() {
                {
                    SELECT("id, organization_id, organization_code, organization_name, item_id, part_no, part_name, uom_code, primary_uom_code, part_type, price, category1, category2, onhand_qty, onroad_qty, last_buy_date, last_buy_amount, total_onhand_qty, system_flag");
                    FROM("ems_partdetail_v");
                    WHERE("system_flag = 'Y'");
                    if (StringUtils.isNotBlank(part_no)) {
                        if (part_no.length() == 9) {
                            WHERE("part_no = #{part_no}");
                        } else {
                            WHERE("part_no like '"+part_no+"%'");
                        }
                    }
                    if (org_id != null && org_id != 0) {
                        WHERE("organization_id = #{org_id}");
                    }
                }
            }.toString();
        }
    }
}
