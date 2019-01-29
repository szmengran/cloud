package com.suntak.cloud.oa.service.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.oa.entity.Cux_oa_qywx_jjjchz_v;
import com.suntak.cloud.oa.mapper.JjjchzMapper;
import com.suntak.cloud.oa.service.JjjchzService;

/**
 * @Package com.suntak.cloud.oa.service.impl
 * @Description: TODO
 * @date Jan 28, 2019 9:09:25 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class JjjchzServiceImpl implements JjjchzService {

	@Autowired
	private JjjchzMapper jjjchzMapper;
	
	@Override
	public List<Cux_oa_qywx_jjjchz_v> findJjjcByConditions() throws Exception {
		return jjjchzMapper.findJjjcByConditions();
	}
	
	@Override
	public Cux_oa_qywx_jjjchz_v findById(String id) throws Exception {
		Cux_oa_qywx_jjjchz_v cux_oa_qywx_jjjchz_v = new Cux_oa_qywx_jjjchz_v();
		cux_oa_qywx_jjjchz_v.setId(id);
		return jjjchzMapper.findById(cux_oa_qywx_jjjchz_v);
	}
	
	@Override
	public Boolean updateByFormmainId(Collection<String> collection) throws Exception {
		Iterator<String> iterator = collection.iterator();
		StringBuilder formmain_ids = new StringBuilder();
		while (iterator.hasNext()) {
			formmain_ids.append(",'").append(iterator.next()).append("'");
		}
		return jjjchzMapper.updateByFormmainIds(formmain_ids.deleteCharAt(0).toString()) > 0;
	}
	
	@Override
	public Boolean updateById(String id) throws Exception {
		return jjjchzMapper.updateById(id) > 0;
	}
	
	@Override
	public Boolean signById(String id, String empno, String name) throws Exception {
		return jjjchzMapper.signById(id, empno, name) > 0;
	}
}
