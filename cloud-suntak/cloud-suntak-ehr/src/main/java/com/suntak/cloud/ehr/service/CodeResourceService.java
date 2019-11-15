package com.suntak.cloud.ehr.service;

import java.util.List;

import com.suntak.cloud.ehr.entity.CodeResource;

public interface CodeResourceService {

	/**
	 * 
	 * @description 根据type code查找配置信息
	 * @param c_type_code
	 * @return
	 * @date Nov 14, 2019 8:39:06 AM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<CodeResource> findResource(String c_type_code);
	
}
