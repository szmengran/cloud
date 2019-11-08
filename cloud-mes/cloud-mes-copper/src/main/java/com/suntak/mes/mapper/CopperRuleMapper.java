package com.suntak.mes.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.mes.entity.TMesCopperRule;
import com.suntak.mes.erp.Report;
import com.szmengran.mybatis.utils.mapper.IMapper;

@Mapper
public interface CopperRuleMapper extends IMapper<TMesCopperRule> {
	
	/**
	 * 
	 * @description 寻找规则
	 * @param report
	 * @return
	 * @throws Exception
	 * @date Oct 22, 2019 10:24:22 AM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@SelectProvider(type = SqlProvider.class, method = "findRule")
	List<TMesCopperRule> findRule(Report report) throws Exception;
	
	@Update("update t_mes_copper_rule set validstatus='0', updatestamp=sysdate, update_by=#{empCode} where validstatus='1'")
	int disableAllRule(@Param("empCode") String empCode) throws Exception;
	
	@Update("update t_mes_copper_rule set validstatus='1', verifystamp=sysdate, verify_by=#{empCode} where validstatus='2'")
	int verifyAllRule(@Param("empCode") String empCode) throws Exception;
	
	@Insert(
	         {"<script>",
	            "insert into t_mes_copper_rule ",
	            "(ID,PROJECT,PLATE_TYPE,generic_group_name,times,min_tg_value,max_tg_value,min_thickness,"
	            + "max_thickness,min_proportion,max_proportion,standard,standard_down,standard_up,"
	            + "down_limit,up_limit,lineid,linename,org_id,"
	            + "front_speed,grinding_speed,clear_speed,speed,copper_speed,create_by,createstamp,"
	            + "update_by,updatestamp,verify_by,verifystamp,validstatus) "
	            + "SELECT T_MES_COPPER_RULE.NEXTVAL, A.* FROM ("
		            + "<foreach collection ='list' item='tlist' separator ='union all'>"
		            + "(SELECT #{tlist.project},#{tlist.plate_type},#{tlist.generic_group_name},#{tlist.times},#{tlist.min_tg_value},"
		            + "#{tlist.max_tg_value},#{tlist.min_thickness},#{tlist.max_thickness},#{tlist.min_proportion},#{tlist.max_proportion},"
		            + "#{tlist.standard},#{tlist.standard_down},#{tlist.standard_up},#{tlist.down_limit},#{tlist.up_limit},"
		            + "#{tlist.lineid},#{tlist.linename},#{tlist.org_id},"
		            + "#{tlist.front_speed},#{tlist.grinding_speed},#{tlist.clear_speed},#{tlist.speed},"
		            + "#{tlist.copper_speed},#{tlist.create_by},#{tlist.createstamp}, "
		            + "#{tlist.update_by},#{tlist.updatestamp},#{tlist.verify_by}, "
		            + "#{tlist.verifystamp},#{tlist.validstatus} "
		            + "FROM DUAL)",
	            "</foreach> ) A",
	            "</script>"
	         }
	    )
	int insertBatchForOracle(List<TMesCopperRule> list) throws Exception;
	
	class SqlProvider {
		public String findRule(Map<String, Object> params) {
			return new SQL() {
				{
					SELECT("*");
					FROM("t_mes_copper_rule a");
					WHERE("a.validstatus='1'");
					WHERE("a.org_id = #{org_id}");
					WHERE("a.lineid = #{lineid}");
					WHERE("a.times = #{times}");
					WHERE("#{min_tg_req_} between a.min_tg_value and a.max_tg_value");
					WHERE("#{process_target_thk_} between a.min_thickness and a.max_thickness");
					WHERE("#{aspect_ratio_} between a.min_aspect_ratio_ and a.max_aspect_ratio_");
				}
			}.toString();
		}
	}
}
