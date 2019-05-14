package com.suntak.cloud.oa.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.oa.entity.Cux_oa_qywx_aqscfx_v;
import com.suntak.cloud.oa.mapper.AqscfxMapper;
import com.suntak.cloud.oa.service.OaService;

/** 
 * @Package com.suntak.cloud.oa.service.impl 
 * @Description: 安全责任书服务
 * @date Feb 25, 2019 12:44:45 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service("aqscfxService")
public class AqscfxServiceImpl implements OaService {
	
	@Autowired
	private AqscfxMapper aqscfxMapper;
	
	@Override
	public List<?> findByConditions() throws Exception {
		return aqscfxMapper.findInfoByConditions();
	}

	@Override
	public Object findById(String id) throws Exception {
		Cux_oa_qywx_aqscfx_v cux_oa_qywx_aqscfx_v = new Cux_oa_qywx_aqscfx_v();
		cux_oa_qywx_aqscfx_v.setId(id);
		return aqscfxMapper.findById(cux_oa_qywx_aqscfx_v);
	}

	@Override
	public Boolean updateById(String id) throws Exception {
		return aqscfxMapper.updateById(id) > 0;
	}

	@Override
	public Boolean signById(String id, String empno, String name) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return aqscfxMapper.signById(id, empno, name+" "+sdf.format(new Date())) > 0;
	}

}
