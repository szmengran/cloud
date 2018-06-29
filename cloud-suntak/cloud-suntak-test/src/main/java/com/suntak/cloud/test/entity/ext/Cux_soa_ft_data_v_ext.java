package com.suntak.cloud.test.entity.ext;

import java.sql.Timestamp;

import com.suntak.cloud.test.entity.Cux_soa_ft_data_v;

/**
 * @Package com.suntak.cloud.test.entity.ext
 * @Description: TODO
 * @date 2018年6月25日 下午1:19:01
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Cux_soa_ft_data_v_ext extends Cux_soa_ft_data_v {
	private String empcode;
	private String empname;
	private Timestamp create_time;
	private Timestamp finish_time;
	public String getEmpcode() {
		return empcode;
	}
	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getFinish_time() {
		return finish_time;
	}
	public void setFinish_time(Timestamp finish_time) {
		this.finish_time = finish_time;
	}
}
