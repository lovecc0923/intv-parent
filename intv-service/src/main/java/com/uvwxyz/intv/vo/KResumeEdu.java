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

	@Id
	private ObjectId id;// 教育经历Id
	private String name;// 学校名称
	private int major;// 专业Id：见常量表
	private String majorText;// 专业
	private String majorDesc;// 专业描述
	private int degree;// 学历Id：见常量表
	private int isForeign;// 是否海外学习：0=否；1=是
	private long begin;// 开始时间
	private long end;// 结束时间

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