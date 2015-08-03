package com.shiku.mianshi.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xyz.commons.utils.ReqUtil;
import cn.xyz.commons.utils.StringUtil;
import cn.xyz.commons.vo.JSONMessage;
import cn.xyz.mianshi.service.ResumeManager;
import cn.xyz.mianshi.vo.cv.ResumeV2;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping("/resume")
public class ResumeController extends AbstractController {

	@Autowired
	private ResumeManager resumeManager;

	@RequestMapping("/add")
	public JSONMessage add(@RequestParam(defaultValue = "") String text) {
		ResumeV2 resume = StringUtil.isEmpty(text) ? new ResumeV2() : JSON
				.parseObject(text, ResumeV2.class);
		// resume.setResumeName(resumeName);
		resume.setCreateTime(System.currentTimeMillis() / 1000);
		resume.setModifyTime(resume.getCreateTime());
		resume.setUserId(ReqUtil.getUserId());

		Object data = resumeManager.save(ReqUtil.getUserId(), resume);

		return JSONMessage.success(null, data);

	}

	@RequestMapping("/delete")
	public JSONMessage delete(@RequestParam String text) {
		Object data = resumeManager.delete(ReqUtil.getUserId(),
				ReqUtil.parseArray(text));
		return JSONMessage.success(null, data);

	}

	@RequestMapping("/update")
	public JSONMessage update(@RequestParam String resumeId,
			@RequestParam String nodeName, @RequestParam String text) {
		Object data = resumeManager.updateNode(ReqUtil.getUserId(),
				ReqUtil.parseId(resumeId), nodeName,
				com.mongodb.util.JSON.parse(text));
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/get")
	public JSONMessage get(@RequestParam String resumeId,
			@RequestParam(defaultValue = "all") String nodeName) {
		Object data = null;

		if (nodeName.equals("all"))
			data = resumeManager.get(new ObjectId(resumeId));
		else
			data = resumeManager.get(new ObjectId(resumeId), nodeName);

		return JSONMessage.success(null, data);
	}

	@RequestMapping("/getByUserId")
	public JSONMessage getByUserId(@RequestParam int userId,
			@RequestParam(defaultValue = "all") String nodeName) {
		Object data = null;

		if (nodeName.equals("all"))
			data = resumeManager.get(userId);
		else
			data = resumeManager.get(userId, nodeName);

		return JSONMessage.success(null, data);
	}

	@RequestMapping("/list")
	public JSONMessage selectByUserId(
			@RequestParam(defaultValue = "") Integer userId) {
		Object data = resumeManager.selectByUserId(ReqUtil.getUserId());
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/name/list")
	public JSONMessage selectNameByUserId(
			@RequestParam(defaultValue = "") Integer userId) {
		Object data = resumeManager.selectSimpleByUserId(ReqUtil.getUserId());
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/{nodeName}/add")
	public JSONMessage addChild(@RequestParam String resumeId,
			@PathVariable String nodeName, @RequestParam String text) {
		Object data = resumeManager.saveChild(ReqUtil.getUserId(),
				ReqUtil.parseId(resumeId), nodeName, ReqUtil.parseDBObj(text));
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/{nodeName}/update")
	public JSONMessage updateChild(@RequestParam String resumeId,
			@PathVariable String nodeName, @RequestParam String text) {
		Object data = resumeManager.updateChild(ReqUtil.getUserId(),
				ReqUtil.parseId(resumeId), nodeName, ReqUtil.parseDBObj(text));
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/{nodeName}/delete")
	public JSONMessage deleteChild(@RequestParam String resumeId,
			@PathVariable String nodeName, @RequestParam String id) {
		Object data = resumeManager.deleteChild(ReqUtil.getUserId(),
				ReqUtil.parseId(resumeId), nodeName, ReqUtil.parseId(id));
		return JSONMessage.success(null, data);
	}
}
