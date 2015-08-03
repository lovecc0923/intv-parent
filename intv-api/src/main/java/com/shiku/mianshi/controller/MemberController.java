package com.shiku.mianshi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.xyz.commons.utils.ReqUtil;
import cn.xyz.commons.vo.JSONMessage;
import cn.xyz.mianshi.service.CompanyManager;
import cn.xyz.mianshi.service.UserManager;
import cn.xyz.mianshi.vo.User;

@RestController
public class MemberController {

	@Autowired
	private CompanyManager companyManager;
	@Autowired
	private UserManager userManager;

	@RequestMapping("/company/member/invite")
	public JSONMessage invite() {
		return null;
	}

	@RequestMapping("/company/member/add")
	public JSONMessage add() {
		return null;
	}

	@RequestMapping("/company/member/update")
	public JSONMessage update() {
		return null;
	}

	@RequestMapping("/company/member/delete")
	public JSONMessage delete() {
		return null;
	}

	@RequestMapping("/company/member/list")
	public JSONMessage list() {
		int userId = ReqUtil.getUserId();
		User user = userManager.getUser(userId);
		Object data = companyManager.selectMemberByCompanyId(user.getCompanyId());

		return JSONMessage.success(null, data);
	}
}
