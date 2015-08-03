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

	/** 工作经验Id */
	@Id
	private ObjectId id;

	/** 职位Id */
	private ObjectId jobId;

	/** 职能Id */
	private int fnId;

	/** 公司行业Id */
	private int industryId;

	/** 公司性质Id */
	private int natureId;

	/** 公司规模Id */
	private int scaleId;

	/** 公司名称 */
	private String companyName;

	/** 所属部门 */
	private String department;

	/** 工作内容、工作描述 */
	private String desc;

	/** 开始时间 */
	private String begin;

	/** 结束时间 */
	private String end;

	/** 对这家公司隐藏我的信息 */
	private int hideMe;

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

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getHideMe() {
		return hideMe;
	}

	public void setHideMe(int hideMe) {
		this.hideMe = hideMe;
	}

}
