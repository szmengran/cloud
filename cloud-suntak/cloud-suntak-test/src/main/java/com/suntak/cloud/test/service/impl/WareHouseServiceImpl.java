package com.suntak.cloud.test.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.test.entity.T_oa_test_warehouse;
import com.suntak.cloud.test.service.WareHouseService;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.test.service.impl
 * @Description: 库位信息服务
 * @date 2018年6月12日 上午11:28:58
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service("wareHouseService")
public class WareHouseServiceImpl implements WareHouseService{

	@Autowired
	AbstractDao abstractDao;
	
	@Override
	public void insert(T_oa_test_warehouse t_oa_test_warehouse) throws Exception {
		Timestamp createstamp = new Timestamp(System.currentTimeMillis());
		t_oa_test_warehouse.setCreatestamp(createstamp);
		t_oa_test_warehouse.setUpdatestamp(createstamp);
		t_oa_test_warehouse.setValidstatus("1");
		abstractDao.insert(t_oa_test_warehouse);
	}

	@Override
	public List<T_oa_test_warehouse> findByConditions(StringBuffer conditions, Object[] params) throws Exception {
		return abstractDao.findByConditions(T_oa_test_warehouse.class, conditions, params);
	}

	@Override
	public void delete(String warehouse_code) throws Exception {
		Object[] params = new Object[1];
		params[0] = warehouse_code;
		abstractDao.executeSql("update t_oa_test_warehouse set validstatus='0' where warehouse_code=?", params);
	}

}
