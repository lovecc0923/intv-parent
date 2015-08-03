package com.shiku.mianshi.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xyz.commons.utils.ReqUtil;
import cn.xyz.commons.vo.JSONMessage;
import cn.xyz.mianshi.example.CompanyExample;
import cn.xyz.mianshi.service.CompanyManager;
import cn.xyz.mianshi.service.ExamManager;
import cn.xyz.mianshi.service.JobManager;
import cn.xyz.mianshi.service.UserManager;
import cn.xyz.mianshi.vo.CompanyVO;
import cn.xyz.mianshi.vo.User;

import com.google.common.collect.Maps;

@RestController
@RequestMapping("/company")
public class CompanyController extends AbstractController {

	private static Logger logger = LoggerFactory
			.getLogger(CompanyController.class);

	@Autowired
	private CompanyManager companyManager;
	@Autowired
	private ExamManager examManager;
	@Autowired
	private JobManager jobManager;
	@Autowired
	private UserManager userManager;

	@RequestMapping("/pay_mode/change")
	public JSONMessage changePayMode(@RequestParam int payMode) {
		User user = userManager.getUser(ReqUtil.getUserId());
		CompanyVO company = new CompanyVO();
		company.setId(user.getCompanyId());
		company.setPayMode(payMode);

		companyManager.update(company);

		return JSONMessage.success();
	}

	@RequestMapping("/get")
	public JSONMessage get(@RequestParam int companyId) {
		Object data = companyManager.get(companyId);

		return JSONMessage.success(null, data);
	}

	@RequestMapping("/getByUserId")
	public JSONMessage getByUserId(@RequestParam int userId) {
		Object data = companyManager.get(userManager.getUser(userId)
				.getCompanyId());

		return JSONMessage.success(null, data);
	}

	@RequestMapping("/get/simple")
	public JSONMessage getSimple() {
		User user = userManager.getUser(ReqUtil.getUserId());
		CompanyVO company = companyManager.get(user.getCompanyId());

		Map<String, Object> data = Maps.newHashMap();
		data.put("companyId", company.getCompanyId());
		data.put("inviteCode", company.getInviteCode());
		data.put("total", company.getTotal());
		data.put("balance", company.getBalance());
		data.put("payMode", company.getPayMode());
		data.put("vtotal", company.getVtotal());
		data.put("vbalance", company.getVbalance());
		data.put("payEndTime", company.getPayEndTime());
		data.put("isAuth", company.getIsAuth());

		return JSONMessage.success(null, data);
	}

	@RequestMapping("/exam/list")
	public JSONMessage getExamList(@RequestParam int type) {
		JSONMessage jMessage;

		try {
			Object data = examManager.selectByUserId(ReqUtil.getUserId(), type,
					1);

			jMessage = JSONMessage.success(null, data);
		} catch (Exception e) {
			logger.error("获取题库列表失败", e);

			jMessage = JSONMessage.error(e);
		}

		return jMessage;
	}

	@RequestMapping("/query")
	public JSONMessage query(@ModelAttribute CompanyExample example) {
		Object data = companyManager.selectByExample(example);

		return JSONMessage.success(null, data);
	}

	@RequestMapping("/register")
	public JSONMessage register(@ModelAttribute CompanyVO company) {
		int companyId = companyManager.register(company);

		Map<String, Object> data = Maps.newHashMap();
		data.put("companyId", companyId);

		return JSONMessage.success(null, data);
	}

	@RequestMapping("/update")
	public JSONMessage update(@ModelAttribute CompanyExample example) {
		User user = userManager.getUser(ReqUtil.getUserId());

		CompanyVO company = new CompanyVO();
		company.setId(user.getCompanyId());
		if (null != example.getName())
			company.setName(example.getName());
		if (null != example.getDescription())
			company.setDescription(example.getDescription());
		if (null != example.getWebsite())
			company.setWebsite(example.getWebsite());
		if (null != example.getScale())
			company.setScale(example.getScale());
		if (null != example.getIndustryId())
			company.setIndustryId(example.getIndustryId());
		if (null != example.getNatureId())
			company.setNatureId(example.getNatureId());
		if (null != example.getCountryId())
			company.setCountryId(example.getCountryId());
		if (null != example.getProvinceId())
			company.setProvinceId(example.getProvinceId());
		if (null != example.getCityId())
			company.setCityId(example.getCityId());
		if (null != example.getAreaId())
			company.setAreaId(example.getAreaId());
		if (null != example.getLongitude())
			company.setLongitude(example.getLongitude());
		if (null != example.getLatitude())
			company.setLatitude(example.getLatitude());
		if (null != example.getAddress())
			company.setAddress(example.getAddress());
		companyManager.update(company);

		return JSONMessage.success();
	}

}