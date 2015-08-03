package com.shiku.mianshi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xyz.commons.KSMSServiceImpl;
import cn.xyz.commons.vo.JSONMessage;
import cn.xyz.mianshi.service.AdminManager;
import cn.xyz.mianshi.service.UserManager;

@RestController
public class AdminController {

	@Autowired
	private AdminManager adminManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private KSMSServiceImpl pushManager;

	@RequestMapping(value = "/config")
	public JSONMessage getConfig() {
		return JSONMessage.success(null, adminManager.getConfig());
	}

	@RequestMapping(value = "/user/debug")
	public JSONMessage getUser(@RequestParam int userId) {
		return JSONMessage.success(null, userManager.getUser(userId));
	}

	@RequestMapping("/basic/randcode/sendSms")
	public JSONMessage sendSms(@RequestParam String telephone) {
		return pushManager.applyVerify(telephone);
	}

	@RequestMapping(value = "/verify/telephone")
	public JSONMessage virifyTelephone(@RequestParam(value = "telephone", required = true) String telephone) {
		return userManager.isRegister(telephone) ? JSONMessage.failure("手机号已注册") : JSONMessage.success("手机号未注册");
	}

}
