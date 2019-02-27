package com.suntak.cloud.oa.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.oa.entity.Cux_oa_qywx_tdlhzhpj_v;
import com.suntak.cloud.oa.mapper.TdlhzhpjMapper;
import com.suntak.cloud.oa.service.OaService;

/** 
 * @Package com.suntak.cloud.oa.service.impl 
 * @Description: 团队量化综合评价表
 * @date Feb 25, 2019 12:44:45 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service("tdlhzhpjService")
public class TdlhzhpjServiceImpl implements OaService {
	
	@Autowired
	private TdlhzhpjMapper tdlhzhpjMapper;
	
	@Override
	public List<?> findByConditions() throws Exception {
		return tdlhzhpjMapper.findInfoByConditions();
	}

	@Override
	public Object findById(String id) throws Exception {
		Cux_oa_qywx_tdlhzhpj_v cux_oa_qywx_tdlhzhpj_v = new Cux_oa_qywx_tdlhzhpj_v();
		cux_oa_qywx_tdlhzhpj_v.setId(id);
		return tdlhzhpjMapper.findById(cux_oa_qywx_tdlhzhpj_v);
	}

	@Override
	public Boolean updateById(String id) throws Exception {
		return tdlhzhpjMapper.updateById(id) > 0;
	}

	@Override
	public Boolean signById(String id, String empno, String name) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return tdlhzhpjMapper.signById(id, empno, name+" "+sdf.format(new Date())) > 0;
	}
}
