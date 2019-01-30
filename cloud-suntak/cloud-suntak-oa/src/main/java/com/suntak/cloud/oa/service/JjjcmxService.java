package com.suntak.cloud.oa.service;

import java.util.List;

import com.suntak.cloud.oa.entity.Cux_oa_qywx_jjjcmx_v;

/**
 * @Package com.suntak.cloud.oa.service
 * @Description: TODO
 * @date Jan 28, 2019 9:08:46 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface JjjcmxService {
	
	/**
	 * 查询需要发送企业微信通知的人员
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public List<Cux_oa_qywx_jjjcmx_v> findJjjcmxByConditions() throws Exception;
	
	/**
	 * 根据ID查找对应的信息
	 * @param id
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public Cux_oa_qywx_jjjcmx_v findById(String id) throws Exception;
	
	
	/**
	 * 企业微信消息发送成功状态回写
	 * @param collection
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public Boolean updateById(String id) throws Exception;
	
	/**
	 * 员工签名
	 * @param id
	 * @param empno
	 * @param name
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public Boolean signById(String id, String empno, String name) throws Exception;
}
