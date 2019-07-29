package com.suntak.cloud.ems.mapper;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.cloud.ems.entity.Ems_item_use_dept_v;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.ems.mapper 
 * @Description: TODO
 * @date Jul 23, 2019 1:31:50 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface EmsItemUseDeptMapper extends IMapper<Ems_item_use_dept_v> {

    /**
     * 查找部门信息
     * @param organization_id
     * @return
     * @throws Exception      
     * @return: List<Ems_item_use_dept_v>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @SelectProvider(type = SqlProvider.class, method = "findDeptInfo")
    List<Ems_item_use_dept_v> findDeptInfo(@Param("organization_id") Integer organization_id, @Param("keyword") String keyword) throws Exception;
    
    class SqlProvider {
        public String findDeptInfo(Map<String, Object> params) {
            Integer organization_id = (Integer) params.get("organization_id");
            String keyword = (String) params.get("keyword");
            return new SQL() {
                {
                    SELECT("disposition_id,organization_id,segment1,distribution_account");
                    FROM("ems_item_use_dept_v");
                    WHERE("segment1 <> '库存初始化'");
                    if (organization_id != null && organization_id != 0) {
                        WHERE("organization_id = #{organization_id}");
                    }
                    if (StringUtils.isNotBlank(keyword)) {
                        WHERE("segment1 like '%"+keyword+"%'");
                    }
                }
            }.toString();
        }
    }
}
