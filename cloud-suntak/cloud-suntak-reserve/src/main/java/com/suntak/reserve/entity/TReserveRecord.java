package com.suntak.reserve.entity;

import java.sql.Timestamp;

import com.szmengran.mybatis.utils.GeneratedValue;
import com.szmengran.mybatis.utils.GenerationType;
import com.szmengran.mybatis.utils.Table;

@Table(id = "id", name = "t_reserve_record")
public class TReserveRecord {
	
	@GeneratedValue(generator = "seq_t_reserve_record", strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String openid;
	private String contact;
	private String contact_phone;
	private String org_name;
	private String visitor;
	private String phone;
	private Timestamp visitor_time;
	private String idcard;
	private String company;
	private String type;
	private String number_plate;
	private String other_visitor;
	private String belongings;
	private Integer num;
	private String remark;
	private Timestamp createstamp;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContact_phone() {
		return contact_phone;
	}
	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getVisitor() {
		return visitor;
	}
	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Timestamp getVisitor_time() {
		return visitor_time;
	}
	public void setVisitor_time(Timestamp visitor_time) {
		this.visitor_time = visitor_time;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumber_plate() {
		return number_plate;
	}
	public void setNumber_plate(String number_plate) {
		this.number_plate = number_plate;
	}
	public String getOther_visitor() {
		return other_visitor;
	}
	public void setOther_visitor(String other_visitor) {
		this.other_visitor = other_visitor;
	}
	public String getBelongings() {
		return belongings;
	}
	public void setBelongings(String belongings) {
		this.belongings = belongings;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getCreatestamp() {
		return createstamp;
	}
	public void setCreatestamp(Timestamp createstamp) {
		this.createstamp = createstamp;
	}
	
}
