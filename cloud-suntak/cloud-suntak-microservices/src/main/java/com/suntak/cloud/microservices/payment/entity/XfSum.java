package com.suntak.cloud.microservices.payment.entity;
/**
 * @Package com.suntak.cloud.microservices.payment.entity
 * @Description: TODO
 * @date Dec 27, 2018 4:29:46 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class XfSum {
	private String empno;
	private String empname;
	private Integer xfzaocannum; //早餐刷卡次数
	private Float xfzaocan; //早餐消费金额
	private Integer zaocanjcnum; //早餐就餐次数
	private Float xfzaocanbt; //早餐补贴
	private Float xfzaocankk; //早餐补贴
	private Integer xfwucannum; //午餐次数
	private Float xfwucan; //午餐
	private Integer wucanjcnum;
	private Float xfwucanbt;
	private Float xfwucankk; //午餐扣款
	private Integer xfwancannum; //晚餐次数
	private Float xfwancan;
	private Integer wancanjcnum;
	private Float xfwancanbt;
	private Float xfwancankk;
	private Integer xfyecannum; //夜宵
	private Float xfyecan; //夜宵
	private Integer yecanjcnum; //夜宵
	private Float xfyecanbt; //夜宵
	private Float xfyecankk; //夜宵
	private Integer xflingcannum; //凌晨餐次数
	private Float xflingcan; //凌晨餐
	private Integer lingcanjcnum; //凌晨餐就餐次数
	private Float xflingcanbt; //凌晨餐补贴
	private Float xflingcankk; //凌晨餐扣款
	private Float chshimoneycount; // 沙井超市
	private Float chshimoneycount2; // 江门超市
	private Float cs; // 大连超市
	private Float kf9990; // 咖啡机
	private Float kf9991; //
	private Float kf9992; //
	private Float kf; // 大连咖啡
	//早餐补贴标准
	private Float zaocanbt1; //沙井1号食堂
	private Float zaocanbt2; //16号食堂
	private Float zaocanbt3; //江门食堂
	//午餐补贴标准
	private Float wucanbt1; //沙井1号食堂
	private Float wucanbt2; //16号食堂
	private Float wucanbt3; //江门食堂
	//晚餐补贴标准
	private Float wancanbt1; //沙井1号食堂
	private Float wancanbt2; //16号食堂
	private Float wancanbt3; //江门食堂
	//夜宵补贴标准，三地合并最高标准yecanbtBZ,
	private Float yecanbt1; //沙井1号食堂
	private Float yecanbt2; //16号食堂
	private Float yecanbt3; //江门食堂
	//凌晨补贴标准，三地合并最高标准lingcanbtBZ
	private Float lingcanbt1; //沙井1号食堂
	private Float lingcanbt2; //16号食堂
	private Float lingcanbt3; //江门食堂
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public Integer getXfzaocannum() {
		return xfzaocannum;
	}
	public void setXfzaocannum(Integer xfzaocannum) {
		this.xfzaocannum = xfzaocannum;
	}
	public Float getXfzaocan() {
		return xfzaocan;
	}
	public void setXfzaocan(Float xfzaocan) {
		this.xfzaocan = xfzaocan;
	}
	public Integer getZaocanjcnum() {
		return zaocanjcnum;
	}
	public void setZaocanjcnum(Integer zaocanjcnum) {
		this.zaocanjcnum = zaocanjcnum;
	}
	public Float getXfzaocanbt() {
		return xfzaocanbt;
	}
	public void setXfzaocanbt(Float xfzaocanbt) {
		this.xfzaocanbt = xfzaocanbt;
	}
	public Float getXfzaocankk() {
		return xfzaocankk;
	}
	public void setXfzaocankk(Float xfzaocankk) {
		this.xfzaocankk = xfzaocankk;
	}
	public Integer getXfwucannum() {
		return xfwucannum;
	}
	public void setXfwucannum(Integer xfwucannum) {
		this.xfwucannum = xfwucannum;
	}
	public Float getXfwucan() {
		return xfwucan;
	}
	public void setXfwucan(Float xfwucan) {
		this.xfwucan = xfwucan;
	}
	public Integer getWucanjcnum() {
		return wucanjcnum;
	}
	public void setWucanjcnum(Integer wucanjcnum) {
		this.wucanjcnum = wucanjcnum;
	}
	public Float getXfwucanbt() {
		return xfwucanbt;
	}
	public void setXfwucanbt(Float xfwucanbt) {
		this.xfwucanbt = xfwucanbt;
	}
	public Float getXfwucankk() {
		return xfwucankk;
	}
	public void setXfwucankk(Float xfwucankk) {
		this.xfwucankk = xfwucankk;
	}
	public Integer getXfwancannum() {
		return xfwancannum;
	}
	public void setXfwancannum(Integer xfwancannum) {
		this.xfwancannum = xfwancannum;
	}
	public Float getXfwancan() {
		return xfwancan;
	}
	public void setXfwancan(Float xfwancan) {
		this.xfwancan = xfwancan;
	}
	public Integer getWancanjcnum() {
		return wancanjcnum;
	}
	public void setWancanjcnum(Integer wancanjcnum) {
		this.wancanjcnum = wancanjcnum;
	}
	public Float getXfwancanbt() {
		return xfwancanbt;
	}
	public void setXfwancanbt(Float xfwancanbt) {
		this.xfwancanbt = xfwancanbt;
	}
	public Float getXfwancankk() {
		return xfwancankk;
	}
	public void setXfwancankk(Float xfwancankk) {
		this.xfwancankk = xfwancankk;
	}
	public Integer getXfyecannum() {
		return xfyecannum;
	}
	public void setXfyecannum(Integer xfyecannum) {
		this.xfyecannum = xfyecannum;
	}
	public Float getXfyecan() {
		return xfyecan;
	}
	public void setXfyecan(Float xfyecan) {
		this.xfyecan = xfyecan;
	}
	public Integer getYecanjcnum() {
		return yecanjcnum;
	}
	public void setYecanjcnum(Integer yecanjcnum) {
		this.yecanjcnum = yecanjcnum;
	}
	public Float getXfyecanbt() {
		return xfyecanbt;
	}
	public void setXfyecanbt(Float xfyecanbt) {
		this.xfyecanbt = xfyecanbt;
	}
	public Float getXfyecankk() {
		return xfyecankk;
	}
	public void setXfyecankk(Float xfyecankk) {
		this.xfyecankk = xfyecankk;
	}
	public Integer getXflingcannum() {
		return xflingcannum;
	}
	public void setXflingcannum(Integer xflingcannum) {
		this.xflingcannum = xflingcannum;
	}
	public Float getXflingcan() {
		return xflingcan;
	}
	public void setXflingcan(Float xflingcan) {
		this.xflingcan = xflingcan;
	}
	public Integer getLingcanjcnum() {
		return lingcanjcnum;
	}
	public void setLingcanjcnum(Integer lingcanjcnum) {
		this.lingcanjcnum = lingcanjcnum;
	}
	public Float getXflingcanbt() {
		return xflingcanbt;
	}
	public void setXflingcanbt(Float xflingcanbt) {
		this.xflingcanbt = xflingcanbt;
	}
	public Float getXflingcankk() {
		return xflingcankk;
	}
	public void setXflingcankk(Float xflingcankk) {
		this.xflingcankk = xflingcankk;
	}
	public Float getChshimoneycount() {
		return chshimoneycount;
	}
	public void setChshimoneycount(Float chshimoneycount) {
		this.chshimoneycount = chshimoneycount;
	}
	public Float getChshimoneycount2() {
		return chshimoneycount2;
	}
	public void setChshimoneycount2(Float chshimoneycount2) {
		this.chshimoneycount2 = chshimoneycount2;
	}
	public Float getKf9990() {
		return kf9990;
	}
	public void setKf9990(Float kf9990) {
		this.kf9990 = kf9990;
	}
	public Float getKf9991() {
		return kf9991;
	}
	public void setKf9991(Float kf9991) {
		this.kf9991 = kf9991;
	}
	public Float getKf9992() {
		return kf9992;
	}
	public void setKf9992(Float kf9992) {
		this.kf9992 = kf9992;
	}
	public Float getZaocanbt1() {
		return zaocanbt1;
	}
	public void setZaocanbt1(Float zaocanbt1) {
		this.zaocanbt1 = zaocanbt1;
	}
	public Float getZaocanbt2() {
		return zaocanbt2;
	}
	public void setZaocanbt2(Float zaocanbt2) {
		this.zaocanbt2 = zaocanbt2;
	}
	public Float getZaocanbt3() {
		return zaocanbt3;
	}
	public void setZaocanbt3(Float zaocanbt3) {
		this.zaocanbt3 = zaocanbt3;
	}
	public Float getWucanbt1() {
		return wucanbt1;
	}
	public void setWucanbt1(Float wucanbt1) {
		this.wucanbt1 = wucanbt1;
	}
	public Float getWucanbt2() {
		return wucanbt2;
	}
	public void setWucanbt2(Float wucanbt2) {
		this.wucanbt2 = wucanbt2;
	}
	public Float getWucanbt3() {
		return wucanbt3;
	}
	public void setWucanbt3(Float wucanbt3) {
		this.wucanbt3 = wucanbt3;
	}
	public Float getWancanbt1() {
		return wancanbt1;
	}
	public void setWancanbt1(Float wancanbt1) {
		this.wancanbt1 = wancanbt1;
	}
	public Float getWancanbt2() {
		return wancanbt2;
	}
	public void setWancanbt2(Float wancanbt2) {
		this.wancanbt2 = wancanbt2;
	}
	public Float getWancanbt3() {
		return wancanbt3;
	}
	public void setWancanbt3(Float wancanbt3) {
		this.wancanbt3 = wancanbt3;
	}
	public Float getYecanbt1() {
		return yecanbt1;
	}
	public void setYecanbt1(Float yecanbt1) {
		this.yecanbt1 = yecanbt1;
	}
	public Float getYecanbt2() {
		return yecanbt2;
	}
	public void setYecanbt2(Float yecanbt2) {
		this.yecanbt2 = yecanbt2;
	}
	public Float getYecanbt3() {
		return yecanbt3;
	}
	public void setYecanbt3(Float yecanbt3) {
		this.yecanbt3 = yecanbt3;
	}
	public Float getLingcanbt1() {
		return lingcanbt1;
	}
	public void setLingcanbt1(Float lingcanbt1) {
		this.lingcanbt1 = lingcanbt1;
	}
	public Float getLingcanbt2() {
		return lingcanbt2;
	}
	public void setLingcanbt2(Float lingcanbt2) {
		this.lingcanbt2 = lingcanbt2;
	}
	public Float getLingcanbt3() {
		return lingcanbt3;
	}
	public void setLingcanbt3(Float lingcanbt3) {
		this.lingcanbt3 = lingcanbt3;
	}
	public Float getCs() {
		return cs;
	}
	public void setCs(Float cs) {
		this.cs = cs;
	}
	public Float getKf() {
		return kf;
	}
	public void setKf(Float kf) {
		this.kf = kf;
	}
}
