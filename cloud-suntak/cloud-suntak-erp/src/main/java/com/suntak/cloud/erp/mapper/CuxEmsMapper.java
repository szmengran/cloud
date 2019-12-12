package com.suntak.cloud.erp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface CuxEmsMapper {

	@Select("{CALL apps.CUX_EMS_WEB_SERVICE_PKG.PRO_MISCELL_TRANSACTION("
			+ "#{retcode, mode=OUT, jdbcType=INTEGER},"
			+ "#{errbuf, mode=OUT, jdbcType=VARCHAR},"
			+ "#{p_org_id, mode=IN, jdbcType=INTEGER},"
			+ "#{p_Header_Id, mode=IN, jdbcType=BIGINT},"
            + "#{x_ebs_number, mode=OUT, jdbcType=VARCHAR})}")
	@Results({
		@Result(column="retcode", property="retcode", jdbcType= JdbcType.INTEGER),
		@Result(column="errbuf", property="errbuf", jdbcType= JdbcType.VARCHAR),
		@Result(column="x_ebs_number", property="x_ebs_number", jdbcType= JdbcType.VARCHAR)
	})
	@Options(statementType = StatementType.CALLABLE)
	void submitEBS(@Param("retcode") Integer retcode, @Param("errbuf") String errbuf, @Param("p_org_id") Integer p_org_id, @Param("p_Header_Id") Long p_Header_Id, @Param("x_ebs_number") String x_ebs_number);
	
}
