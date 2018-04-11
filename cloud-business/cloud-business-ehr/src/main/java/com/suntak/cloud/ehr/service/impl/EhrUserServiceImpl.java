package com.suntak.cloud.ehr.service.impl;
/**
 * @Package com.suntak.cloud.ehr.service.impl
 * @Description: EHR用户的信息
 * @date 2018年4月11日 下午2:26:47
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */

import java.util.List;

import org.springframework.stereotype.Service;

import com.suntak.cloud.ehr.service.EhrUserService;
import com.suntak.ehr.entity.EhrUser;
import com.szmengran.common.service.AbstractService;

@Service
public class EhrUserServiceImpl extends AbstractService implements EhrUserService{

	@Override
	public List<EhrUser> findByCondition(String conditions) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT EMPCODE,EMPNAME,C_MOBILE_TEL PHONE FROM tb_v_rpt_emp_info WHERE ").append(conditions);
		return super.findBySql(new EhrUser(), sql.toString(), null);
	}

}
