package com.suntak.cloud.recruitment.entity;

import java.sql.Timestamp;

import com.szmengran.mybatis.utils.GeneratedValue;
import com.szmengran.mybatis.utils.GenerationType;
import com.szmengran.mybatis.utils.Table;

/**
 * @Package com.suntak.cloud.recruitment.entity
 * @Description: 任务表
 * @date 2018年8月17日 下午4:42:27
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "taskid")
public class T_hr_task {
    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_HR_TASK")
	private String taskid;
	private String applicantid;
	private Integer subflowid;
	private String assignrole;
	private String assign;
	private String remark;
	private String agree;
	private Short status;
	private String attribute1;
	private String attribute2;
	private String attribute3;
	private String attribute4;
	private String attribute5;
	private String attribute6;
	private String attribute7;
	private String attribute8;
	private String attribute9;
	private String attribute10;
	private String attribute11;
	private String attribute12;
	private String attribute13;
	private String attribute14;
	private String attribute15;
	private Timestamp createstamp;
	private Timestamp updatestamp;
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(String applicantid) {
		this.applicantid = applicantid;
	}
	public String getAssignrole() {
		return assignrole;
	}
	public void setAssignrole(String assignrole) {
		this.assignrole = assignrole;
	}
	public String getAssign() {
		return assign;
	}
	public void setAssign(String assign) {
		this.assign = assign;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAgree() {
		return agree;
	}
	public void setAgree(String agree) {
		this.agree = agree;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getAttribute1() {
        return attribute1;
    }
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }
    public String getAttribute2() {
        return attribute2;
    }
    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }
    public String getAttribute3() {
        return attribute3;
    }
    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }
    public String getAttribute4() {
        return attribute4;
    }
    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }
    public String getAttribute5() {
        return attribute5;
    }
    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }
    public String getAttribute6() {
        return attribute6;
    }
    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }
    public String getAttribute7() {
        return attribute7;
    }
    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }
    public String getAttribute8() {
        return attribute8;
    }
    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }
    public String getAttribute9() {
        return attribute9;
    }
    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }
    public String getAttribute10() {
        return attribute10;
    }
    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }
    public String getAttribute11() {
        return attribute11;
    }
    public void setAttribute11(String attribute11) {
        this.attribute11 = attribute11;
    }
    public String getAttribute12() {
        return attribute12;
    }
    public void setAttribute12(String attribute12) {
        this.attribute12 = attribute12;
    }
    public String getAttribute13() {
        return attribute13;
    }
    public void setAttribute13(String attribute13) {
        this.attribute13 = attribute13;
    }
    public String getAttribute14() {
        return attribute14;
    }
    public void setAttribute14(String attribute14) {
        this.attribute14 = attribute14;
    }
    public String getAttribute15() {
        return attribute15;
    }
    public void setAttribute15(String attribute15) {
        this.attribute15 = attribute15;
    }
    public Timestamp getCreatestamp() {
		return createstamp;
	}
	public void setCreatestamp(Timestamp createstamp) {
		this.createstamp = createstamp;
	}
	public Integer getSubflowid() {
		return subflowid;
	}
	public void setSubflowid(Integer subflowid) {
		this.subflowid = subflowid;
	}
	public Timestamp getUpdatestamp() {
		return updatestamp;
	}
	public void setUpdatestamp(Timestamp updatestamp) {
		this.updatestamp = updatestamp;
	}
}
