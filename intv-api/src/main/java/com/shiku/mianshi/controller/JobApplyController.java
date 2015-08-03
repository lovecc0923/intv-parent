package com.shiku.mianshi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xyz.commons.utils.ReflectionUtils;
import cn.xyz.commons.utils.ReqUtil;
import cn.xyz.commons.vo.JSONMessage;
import cn.xyz.mianshi.example.JobApplyExample;
import cn.xyz.mianshi.service.JobApplyManager;

@RestController
public class JobApplyController {
	@Autowired
	private JobApplyManager jobApplyManager;

	/** 职位申请 */
	@RequestMapping("/job/apply")
	public JSONMessage save(@RequestParam(defaultValue = "") String resumeId, @RequestParam String text) {
		Object data = jobApplyManager.save(ReqUtil.getUserId(), ReqUtil.parseId(resumeId), ReqUtil.parseArray(text));
		return JSONMessage.success(null, data);
	}

	/** 同意职位申请 */
	@RequestMapping("/job/apply/agree")
	public JSONMessage agree(@RequestParam String text, @RequestParam(defaultValue = "0") long startTime) {
		Object data = jobApplyManager.agree(ReqUtil.getUserId(), ReqUtil.parseArray(text), startTime);
		return JSONMessage.success(null, data);
	}

	/** 取消职位申请 */
	@RequestMapping("/job/apply/delete")
	public JSONMessage delete(@RequestParam String text) {
		jobApplyManager.delete(ReqUtil.getUserId(), ReqUtil.parseArray(text));
		return JSONMessage.success();
	}

	@RequestMapping("/job/apply/read")
	public JSONMessage read(@RequestParam String text) {
		jobApplyManager.read(ReqUtil.getUserId(), ReqUtil.parseArray(text), 1);
		return JSONMessage.success();
	}

	@RequestMapping("/job/apply/unread")
	public JSONMessage unread(@RequestParam String text) {
		jobApplyManager.read(ReqUtil.getUserId(), ReqUtil.parseArray(text), 0);
		return JSONMessage.success();
	}

	/** 拒绝职位申请 */
	@RequestMapping("/job/apply/refuse")
	public JSONMessage refuse(@RequestParam String text) {
		Object data = jobApplyManager.refuse(ReqUtil.getUserId(), ReqUtil.parseArray(text));

		return JSONMessage.success(null, data);
	}

	@RequestMapping("/job/apply/tag")
	public JSONMessage tag(@RequestParam(defaultValue = "") String applyId, @RequestParam(defaultValue = "") Integer tagId) {
		jobApplyManager.tag(ReqUtil.parseId(applyId), tagId);

		return JSONMessage.success();
	}

	/** 职位申请记录 */
	@RequestMapping("/job/apply/list")
	public JSONMessage selectByExample(HttpServletRequest request) {
		JobApplyExample example = ReflectionUtils.parse(request, JobApplyExample.class);
		Object data = jobApplyManager.selectByExample(ReqUtil.getUserId(), example);
		return JSONMessage.success(null, data);
	}

}
