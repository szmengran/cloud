package com.suntak.cloud.ehr.mapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.suntak.cloud.ehr.entity.ContactExt;
import com.szmengran.mybatis.utils.mapper.IMapper;

/**
 * @Package com.suntak.cloud.ehr.mapper
 * @Description: 联系人
 * @date Nov 2, 2018 8:10:28 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface ContactMapper extends IMapper<ContactExt>{
	
	@SelectProvider(type = SqlProvider.class, method = "findContact")
	public List<ContactExt> findContact(@Param("currentDate") Timestamp currentDate) throws Exception;
	
	@SelectProvider(type = SqlProvider.class, method = "findDisableContact")
	public List<ContactExt> findDisableContact() throws Exception;
	
	class SqlProvider {
		public String findContact(Map<String, Object> param){
			StringBuilder strSql = new StringBuilder();
			strSql.append("select a.empcode userid, a.empname name, a.c_mobile_tel mobile,")
			      .append("b.id deptid, nvl(a.job_level,a.posname) position, decode(gender,'男','1','0') gender, 1 enable,")
			      .append("labordate, operate_time, mobile_operate_time,short_tel")
			      .append(" from tb_v_rpt_oa_emp_info a left join")
			      .append(" (select a.*,b.name pname from t_wechat_department a left join t_wechat_department b")
			      .append(" on a.parentid = b.id ) b on (b.name = kename and b.pname = nvl(person_attr, companyname))")
			      .append(" or (kename is null and b.name = nvl(person_attr, companyname))")
//			      .append(" where a.empcode = '010848'");
			      .append(" where a.labordate >= #{currentDate}")
			      .append(" or a.operate_time >= #{currentDate}")
			      .append(" or a.mobile_operate_time >= #{currentDate}");
	        return strSql.toString();
	    }
		
		public String findDisableContact(Map<String, Object> param){
			StringBuilder strSql = new StringBuilder();
			strSql.append("select a.empcode userid, a.empname name, a.c_mobile_tel mobile")
			.append(" from tb_v_rpt_emp_info a")
			.append(" where a.empstatusname='离职'")
			.append(" and to_date(a.exitDate, 'yyyy-mm-dd') > sysdate - 1");
			return strSql.toString();
		}
	}
}
