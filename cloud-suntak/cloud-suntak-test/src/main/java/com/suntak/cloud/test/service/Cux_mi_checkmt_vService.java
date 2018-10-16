package com.suntak.cloud.test.service;

import java.util.List;

import com.suntak.cloud.test.entity.Cux_mi_checkmt_v;

/**
 * @Package com.suntak.cloud.test.service
 * @Description: 冶具服务
 * @date 2018年10月16日 上午11:16:50
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface Cux_mi_checkmt_vService {

	List<Cux_mi_checkmt_v> findByConditions(String org_id, String segment1) throws Exception;
}
