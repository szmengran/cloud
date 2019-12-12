package com.suntak.cloud.erp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.mes.erp.Report;
import com.szmengran.mybatis.utils.mapper.IMapper;

@Mapper
public interface ReportMapper extends IMapper<Report> {

	/**
	 * 
	 * @description 根据工单号查找工单信息
	 * @param wipEntityName
	 * @return
	 * @throws Exception
	 * @date Oct 22, 2019 3:30:02 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@SelectProvider(type = SqlProvider.class, method = "findReportByWipEntityName")
	List<Report> findReportByWipEntityName(@Param("wipEntityName") String wipEntityName, @Param("organizationId") Integer organizationId) throws Exception;
	
	/**
	 * 
	 * @description 根据生产型号查找工单信息
	 * @param primaryItem
	 * @return
	 * @throws Exception
	 * @date Oct 22, 2019 3:30:29 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@SelectProvider(type = SqlProvider.class, method = "findReportByPrimaryItem")
	List<Report> findReportByPrimaryItem(@Param("primaryItem") String primaryItem, @Param("organizationId") Integer organizationId) throws Exception;
	
	class SqlProvider {
		public String findReportByWipEntityName(Map<String, Object> params) {
			return new SQL() {
				{
					SELECT("*");
					FROM("cux_prod_report_wip_for_mes a");
					WHERE("a.organization_id = #{organizationId}");
					WHERE("a.wip_entity_name = #{wipEntityName}");
				}
			}.toString();
		}
		public String findReportByPrimaryItem(Map<String, Object> params) {
			String primaryItem = (String)params.get("primaryItem");
			return new SQL() {
				{
					SELECT("distinct PROCESS_TARGET_THK_,ASPECT_RATIO_,MIN_TG_REQ_,GENERIC_GROUP_NAME,PROJECT,BORDERTYPE");
					FROM("cux_prod_report_item_for_mes a");
					WHERE("a.organization_id = #{organizationId}");
					WHERE("a.segment1 like '%"+primaryItem+"%'");
				}
			}.toString();
		}
	}
}
