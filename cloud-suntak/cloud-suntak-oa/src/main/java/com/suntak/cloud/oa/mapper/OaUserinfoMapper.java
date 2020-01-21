package com.suntak.cloud.oa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.cloud.oa.entity.Oa_user_info;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.oa.mapper 
 * @Description: OA用户信息查询
 * @date Jul 8, 2019 4:05:00 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface OaUserinfoMapper extends IMapper<Oa_user_info> {
    
    @SelectProvider(type = SqlProvider.class, method = "findByCode")
    List<Oa_user_info> findByCode(@Param("code") String code) throws Exception;
    
    class SqlProvider {
        public String findByCode() {
            return new SQL(){
                {
                    SELECT("a.id, a.name, a.code, a.org_department_id, b.login_name, c.name deptname, c.code deptcode,a.org_post_id,d.name postname,d.code postcode,a.org_level_id,e.name levelname,e.code levelcode");
                    FROM("org_member a");
                    LEFT_OUTER_JOIN("org_principal b on a.id = b.member_id");
                    LEFT_OUTER_JOIN("org_unit c on a.org_department_id = c.id");
                    LEFT_OUTER_JOIN("org_post d on a.org_post_id = d.id");
                    LEFT_OUTER_JOIN("org_level e on a.org_level_id = e.id");
                    WHERE("b.login_name is not null");
                    WHERE("a.is_deleted=0");
                    WHERE("a.code = #{code}");
                }
            }.toString();
        }
    }
    
}
