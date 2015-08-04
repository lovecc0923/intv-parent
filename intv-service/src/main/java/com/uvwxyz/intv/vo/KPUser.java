package com.uvwxyz.intv.vo;

/**
 * 个人用户
 * 
 * @author Administrator
 *
 */
public class KPUser {
	private int userId;// 用户Id
	private String name;// 姓名
	private int sex;// 性别：0=女；1=男
	private int degree;// 学历：见常量表
	private int workTime;// 工作年限
	private String weixin;// 微信号

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int getWorkTime() {
		return workTime;
	}

	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

}
