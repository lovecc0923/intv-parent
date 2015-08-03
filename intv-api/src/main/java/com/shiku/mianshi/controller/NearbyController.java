package com.shiku.mianshi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.xyz.commons.vo.JSONMessage;
import cn.xyz.mianshi.service.CompanyManager;
import cn.xyz.mianshi.service.JobManager;

import com.shiku.lbs.service.NearbyManager;
import com.shiku.lbs.vo.BasePoi;
import com.shiku.lbs.vo.NearbyUser;

@RestController
@RequestMapping
public class NearbyController {

	@Autowired
	private NearbyManager nearbyManager;
	@Autowired
	private JobManager jobManager;
	@Autowired
	private CompanyManager companyManager;

	// @RequestMapping(value = "/user/update")
	// public JSONMessage updateUserPoi(@ModelAttribute NearbyUserPoi poi) {
	// JSONMessage jMessage;
	// try {
	// jMessage = nearbyService.updateUserPoi(poi) ?
	// JSONMessage.wrapSuccess(null) : JSONMessage.wrapFailure(null);
	// } catch (Exception e) {
	// jMessage = JSONMessage.wrapError(e);
	// }
	// return jMessage;
	// }

	@RequestMapping(value = "/nearby/user")
	public JSONMessage getNearbyUser(@ModelAttribute NearbyUser poi) {
		JSONMessage jMessage;
		try {
			Object data = nearbyManager.getUserList(poi);
			jMessage = JSONMessage.success(null, data);
		} catch (Exception e) {
			jMessage = JSONMessage.error(e);
		}
		return jMessage;
	}

	@RequestMapping(value = "/nearby/talents")
	public JSONMessage getNearbyTalents(@ModelAttribute NearbyUser poi) {
		JSONMessage jMessage;
		try {
			Object data = nearbyManager.getTalentsList(poi);
			jMessage = JSONMessage.success(null, data);
		} catch (Exception e) {
			jMessage = JSONMessage.error(e);
		}
		return jMessage;
	}

	@RequestMapping(value = "/nearby/company")
	public JSONMessage getNearbyCompany(@ModelAttribute BasePoi poi) {
		Object data = nearbyManager.getCompanyList(poi);
		return JSONMessage.success(null, data);
	}

	@RequestMapping(value = "/nearby/job")
	public JSONMessage getNearbyJob(@ModelAttribute BasePoi poi) {
		List<Integer> idList = nearbyManager.getCompanyIdList(poi);
		Object data = jobManager.selectByCompanyId(idList, poi.getPageIndex(), poi.getPageSize());
		return JSONMessage.success(null, data);

	}

	// @RequestMapping(value = "/nearby/company")
	// public JSONMessage getNearbyCompany(@ModelAttribute NearbyCompany poi) {
	// JSONMessage jMessage;
	// try {
	// Object data = nearbyManager.selectCompanyList(poi);
	// jMessage = JSONMessage.wrapSuccess(null, data);
	// } catch (Exception e) {
	// jMessage = JSONMessage.wrapError(e);
	// }
	// return jMessage;
	// }

	// @RequestMapping(value = "/nearby/job")
	// public JSONMessage getNearbyJob(@ModelAttribute NearbyJob poi) {
	// JSONMessage jMessage;
	// try {
	// Object data = nerarbyManager.nearbyJobList(poi);
	// jMessage = JSONMessage.wrapSuccess(null, data);
	// } catch (Exception e) {
	// jMessage = JSONMessage.wrapError(e);
	// }
	// return jMessage;
	// }

}
