package com.uvwxyz.intv.vo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

/**
 * 教育经历
 * 
 * @author Administrator
 *
 */
public class KResumeEdu {

	/** 教育经历Id */
	@Id
	private ObjectId id;

	/** 学校名称 */
	private String name;

	/** 专业Id */
	private int major;

	/** 专业 */
	private String majorText;

	/** 专业描述 */
	private String majorDesc;

	/** 学历Id */
	private int degree;

	/** 是否海外学习 */
	private int isForeign;

	/** 开始时间 */
	private long begin;

	/** 结束时间 */
	private long end;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public String getMajorText() {
		return majorText;
	}

	public void setMajorText(String majorText) {
		this.majorText = majorText;
	}

	public String getMajorDesc() {
		return majorDesc;
	}

	public void setMajorDesc(String majorDesc) {
		this.majorDesc = majorDesc;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int getIsForeign() {
		return isForeign;
	}

	public void setIsForeign(int isForeign) {
		this.isForeign = isForeign;
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

}