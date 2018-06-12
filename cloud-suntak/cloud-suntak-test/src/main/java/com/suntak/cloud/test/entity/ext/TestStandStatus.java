package com.suntak.cloud.test.entity.ext;
/**
 * @Package com.suntak.cloud.test.entity.ext
 * @Description: 测试架状态
 * @date 2018年6月12日 上午9:38:35
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public enum TestStandStatus {

	INVAILD("无效","0"),VALID("有效","1"),INUSE("使用中","2");
	
	public String name;
	public String status;
	private TestStandStatus(String name, String status) {
		this.name = name;
		this.status = status;
	}
}
