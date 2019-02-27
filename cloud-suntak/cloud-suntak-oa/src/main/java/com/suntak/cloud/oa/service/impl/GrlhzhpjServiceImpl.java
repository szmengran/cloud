package com.suntak.cloud.oa.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.oa.entity.Cux_oa_qywx_grlhzhpj_v;
import com.suntak.cloud.oa.mapper.GrlhzhpjMapper;
import com.suntak.cloud.oa.service.OaService;

/** 
 * @Package com.suntak.cloud.oa.service.impl 
 * @Description: 团队量化综合评价表
 * @date Feb 25, 2019 12:44:45 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service("grlhzhpjService")
public class GrlhzhpjServiceImpl implements OaService {
	
	@Autowired
	private GrlhzhpjMapper grlhzhpjMapper;
	
	@Override
	public List<?> findByConditions() throws Exception {
		return grlhzhpjMapper.findInfoByConditions();
	}

	@Override
	public Object findById(String id) throws Exception {
		Cux_oa_qywx_grlhzhpj_v cux_oa_qywx_grlhzhpj_v = new Cux_oa_qywx_grlhzhpj_v();
		cux_oa_qywx_grlhzhpj_v.setId(id);
		return grlhzhpjMapper.findById(cux_oa_qywx_grlhzhpj_v);
	}

	@Override
	public Boolean updateById(String id) throws Exception {
		return grlhzhpjMapper.updateById(id) > 0;
	}

	@Override
	public Boolean signById(String id, String empno, String name) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return grlhzhpjMapper.signById(id, empno, name+" "+sdf.format(new Date())) > 0;
	}

}
