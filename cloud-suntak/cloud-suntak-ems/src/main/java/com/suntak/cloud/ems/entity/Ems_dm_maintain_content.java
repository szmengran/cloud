package com.suntak.cloud.ems.entity;

import com.szmengran.mybatis.utils.GeneratedValue;
import com.szmengran.mybatis.utils.GenerationType;
import com.szmengran.mybatis.utils.Table;

/** 
 * @Package com.suntak.cloud.ems.entity 
 * @Description: 保养明细项目
 * @date Aug 23, 2019 1:11:57 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "id")
public class Ems_dm_maintain_content {
    
    @GeneratedValue(generator="HIBERNATE_SEQUENCE", strategy=GenerationType.SEQUENCE)
    private Long id;
    private Long maintain_id;
    private Integer index_;
    private String content;
    private String m_check;
    private String m_clear;
    private String m_adjust;
    private String m_lubric;
    private String m_repair;
    private String m_change;
    private String exe_detail;
    private String p_check;
    private String p_clear;
    private String p_change;
    private String p_gasup;
    private String p_correction;
    private String p_fastening;
    private String p_dredge;
    private String p_temperature;
    public Long getMaintain_id() {
        return maintain_id;
    }
    public void setMaintain_id(Long maintain_id) {
        this.maintain_id = maintain_id;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getIndex_() {
        return index_;
    }
    public void setIndex_(Integer index_) {
        this.index_ = index_;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getM_check() {
        return m_check;
    }
    public void setM_check(String m_check) {
        this.m_check = m_check;
    }
    public String getM_clear() {
        return m_clear;
    }
    public void setM_clear(String m_clear) {
        this.m_clear = m_clear;
    }
    public String getM_adjust() {
        return m_adjust;
    }
    public void setM_adjust(String m_adjust) {
        this.m_adjust = m_adjust;
    }
    public String getM_lubric() {
        return m_lubric;
    }
    public void setM_lubric(String m_lubric) {
        this.m_lubric = m_lubric;
    }
    public String getM_repair() {
        return m_repair;
    }
    public void setM_repair(String m_repair) {
        this.m_repair = m_repair;
    }
    public String getM_change() {
        return m_change;
    }
    public void setM_change(String m_change) {
        this.m_change = m_change;
    }
    public String getExe_detail() {
        return exe_detail;
    }
    public void setExe_detail(String exe_detail) {
        this.exe_detail = exe_detail;
    }
    public String getP_check() {
        return p_check;
    }
    public void setP_check(String p_check) {
        this.p_check = p_check;
    }
    public String getP_clear() {
        return p_clear;
    }
    public void setP_clear(String p_clear) {
        this.p_clear = p_clear;
    }
    public String getP_change() {
        return p_change;
    }
    public void setP_change(String p_change) {
        this.p_change = p_change;
    }
    public String getP_gasup() {
        return p_gasup;
    }
    public void setP_gasup(String p_gasup) {
        this.p_gasup = p_gasup;
    }
    public String getP_correction() {
        return p_correction;
    }
    public void setP_correction(String p_correction) {
        this.p_correction = p_correction;
    }
    public String getP_fastening() {
        return p_fastening;
    }
    public void setP_fastening(String p_fastening) {
        this.p_fastening = p_fastening;
    }
    public String getP_dredge() {
        return p_dredge;
    }
    public void setP_dredge(String p_dredge) {
        this.p_dredge = p_dredge;
    }
    public String getP_temperature() {
        return p_temperature;
    }
    public void setP_temperature(String p_temperature) {
        this.p_temperature = p_temperature;
    }
}
