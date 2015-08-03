package com.uvwxyz.intv.vo;

import java.util.List;

import org.mongodb.morphia.annotations.Id;

/**
 * 用户简历
 * 
 * @author Administrator
 *
 */
public class KResume {

	/** 用户Id */
	@Id
	private int userId;

	/** 期望职位 */
	private int jobId;

	/** 薪水要求：0=面议；1=按区间 */
	private int salary;

	/** 最低薪资 */
	private int min;

	/** 最高薪资 */
	private int max;

	/** 求职状态 */
	private int jobStatus;

	/** 我的亮点 */
	private List<String> tags;

	/** 我的优势 */
	private String forte;

	/** 工作经历 */
	private List<KResumeWork> workList;

	/** 教育经历 */
	private List<KResumeEdu> eduList;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(int jobStatus) {
		this.jobStatus = jobStatus;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getForte() {
		return forte;
	}

	public void setForte(String forte) {
		this.forte = forte;
	}

	public List<KResumeWork> getWorkList() {
		return workList;
	}

	public void setWorkList(List<KResumeWork> workList) {
		this.workList = workList;
	}

	public List<KResumeEdu> getEduList() {
		return eduList;
	}

	public void setEduList(List<KResumeEdu> eduList) {
		this.eduList = eduList;
	}

}
