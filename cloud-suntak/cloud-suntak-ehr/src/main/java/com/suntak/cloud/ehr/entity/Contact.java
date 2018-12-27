package com.suntak.cloud.ehr.entity;

import com.szmengran.mybatis.utils.Table;

/**
 * @Package com.suntak.cloud.ehr.entity
 * @Description: 企业微信通讯录人员信息
 * @date Dec 17, 2018 10:04:34 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "userid")
public class Contact {
	private String userid; //成员UserID
	private String name; //成员名称
	private String alias; //成员别名
	private String mobile; //手机号码
	private Integer[] department; //成员所属部门id列表,不超过20个
	private Integer[] order; //部门内的排序值，默认为0，成员次序以创建时间从小到大排列。数量必须和department一致，数值越大排序越前面。有效的值范围是[0, 2^32)
	private String position; //职务信息
	private String gender; //性别
	private String email; //邮箱
	private String telephone; //座机
	private String isleader; //上级字段
	private String avatar_mediaid; //成员头像的mediaid
	private Integer enable; //启用/禁用成员
	private Extattr extattr; //自定义字段
	private String to_invite; //是否邀请该成员使用企业微信
	private String external_profile; //成员对外属性
	private String external_position; //对外职务
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer[] getDepartment() {
		return department;
	}
	public void setDepartment(Integer[] department) {
		this.department = department;
	}
	public Integer[] getOrder() {
		return order;
	}
	public void setOrder(Integer[] order) {
		this.order = order;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getIsleader() {
		return isleader;
	}
	public void setIsleader(String isleader) {
		this.isleader = isleader;
	}
	public String getAvatar_mediaid() {
		return avatar_mediaid;
	}
	public void setAvatar_mediaid(String avatar_mediaid) {
		this.avatar_mediaid = avatar_mediaid;
	}
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	public Extattr getExtattr() {
		return extattr;
	}
	public void setExtattr(Extattr extattr) {
		this.extattr = extattr;
	}
	public String getTo_invite() {
		return to_invite;
	}
	public void setTo_invite(String to_invite) {
		this.to_invite = to_invite;
	}
	public String getExternal_profile() {
		return external_profile;
	}
	public void setExternal_profile(String external_profile) {
		this.external_profile = external_profile;
	}
	public String getExternal_position() {
		return external_position;
	}
	public void setExternal_position(String external_position) {
		this.external_position = external_position;
	}
}
