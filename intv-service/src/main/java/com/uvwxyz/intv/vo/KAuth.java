package com.uvwxyz.intv.vo;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

/**
 * 用户认证信息
 * 
 * @author uvwxyz@www.youjob.co
 *
 */
@Entity(value = "intv_auth", noClassnameStored = true)
public class KAuth {

	/** 用户Id */
	@Id
	private int userId;

	/** 手机号码 */
	@Indexed
	private String telephone;

	/** 用户密码 */
	private String password;

	/** 用户状态 */
	private int status;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
