package com.suntak.cloud.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.oa.entity.Cux_oa_qywx_jjjcmx_v;
import com.suntak.cloud.oa.mapper.JjjcmxMapper;
import com.suntak.cloud.oa.service.JjjcmxService;

/**
 * @Package com.suntak.cloud.oa.service.impl
 * @Description: TODO
 * @date Jan 28, 2019 9:09:25 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class JjjcmxServiceImpl implements JjjcmxService {

	@Autowired
	private JjjcmxMapper jjjcmxMapper;
	
	@Override
	public List<Cux_oa_qywx_jjjcmx_v> findJjjcmxByConditions() throws Exception {
		return jjjcmxMapper.findJjjcmxByConditions();
	}
	
	@Override
	public Cux_oa_qywx_jjjcmx_v findById(String id) throws Exception {
		Cux_oa_qywx_jjjcmx_v cux_oa_qywx_jjjcmx_v = new Cux_oa_qywx_jjjcmx_v();
		cux_oa_qywx_jjjcmx_v.setId(id);
		return jjjcmxMapper.findById(cux_oa_qywx_jjjcmx_v);
	}
	
	@Override
	public Boolean updateById(String id) throws Exception {
		return jjjcmxMapper.updateById(id) > 0;
	}
	
	@Override
	public Boolean signById(String id, String empno, String name) throws Exception {
		return jjjcmxMapper.signById(id, empno, name) > 0;
	}
}
