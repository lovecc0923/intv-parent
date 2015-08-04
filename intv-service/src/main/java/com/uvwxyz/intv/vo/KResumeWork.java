package com.uvwxyz.intv.vo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

/**
 * 工作经历
 * 
 * @author Administrator
 *
 */
public class KResumeWork {

	@Id
	private ObjectId id;// 工作经验Id
	private ObjectId jobId;// 职位Id
	private int fnId;// 职能Id：见常量表
	private int industryId;// 公司行业Id：见常量表
	private int natureId;// 公司性质Id：见常量表
	private int scaleId;// 公司规模Id：见常量表
	private String companyName;// 公司名称
	private String department;// 所属部门
	private String desc;// 工作内容、工作描述
	private long begin;// 开始时间
	private long end;// 结束时间
	private int hideMe;// 对这家公司隐藏我的信息：0=否；1=是

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getJobId() {
		return jobId;
	}

	public void setJobId(ObjectId jobId) {
		this.jobId = jobId;
	}

	public int getFnId() {
		return fnId;
	}

	public void setFnId(int fnId) {
		this.fnId = fnId;
	}

	public int getIndustryId() {
		return industryId;
	}

	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}

	public int getNatureId() {
		return natureId;
	}

	public void setNatureId(int natureId) {
		this.natureId = natureId;
	}

	public int getScaleId() {
		return scaleId;
	}

	public void setScaleId(int scaleId) {
		this.scaleId = scaleId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getBegin() {
		return begin;
	}

	public void setBegin(long begin) {
		this.begin = begin;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public int getHideMe() {
		return hideMe;
	}

	public void setHideMe(int hideMe) {
		this.hideMe = hideMe;
	}

}
