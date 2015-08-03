package com.shiku.mianshi.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xyz.commons.utils.ReqUtil;
import cn.xyz.commons.vo.JSONMessage;
import cn.xyz.mianshi.example.AuditionFTExample;
import cn.xyz.mianshi.service.AuditionFTManager;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping("/aft")
public class AuditionFTController {

	@Autowired
	private AuditionFTManager aftManager;

	/** 同意参加初试 */
	@RequestMapping("/agree")
	public JSONMessage agree(@RequestParam String text) {
		Object data = aftManager.agree(ReqUtil.getUserId(), ReqUtil.parseArray(text));
		return JSONMessage.success(null, data);
	}

	/** 拒绝参加初试 */
	@RequestMapping("/refuse")
	public JSONMessage refuse(@RequestParam String text) {
		Object data = aftManager.refuse(ReqUtil.getUserId(), ReqUtil.parseArray(text));
		return JSONMessage.success(null, data);
	}

	/** 删除初试记录 */
	@RequestMapping("/delete")
	public JSONMessage delete(@RequestParam String text) {
		Object data = aftManager.delete(ReqUtil.getUserId(), ReqUtil.parseArray(text));
		return JSONMessage.success(null, data);
	}

	/** 初试不通过 */
	@RequestMapping("/notpass")
	public JSONMessage notpass(@RequestParam String text) {
		Object data = aftManager.notpass(ReqUtil.getUserId(), ReqUtil.parseArray(text));
		return JSONMessage.success(null, data);
	}

	/** 初试通过 */
	@RequestMapping("/pass")
	public JSONMessage pass(@RequestParam String text, @RequestParam(defaultValue = "0") long startTime) {
		Object data = aftManager.pass(ReqUtil.getUserId(), ReqUtil.parseArray(text), startTime);
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/update_exam")
	public JSONMessage updateExam(@RequestParam String aftId, @RequestParam String examList) {
		aftManager.update(ReqUtil.getUserId(), ReqUtil.parseId(aftId), JSON.parseArray(examList, Integer.class));
		return JSONMessage.success();
	}

	/** 获取初试 */
	@RequestMapping("/get")
	public JSONMessage get(@RequestParam String aftId) {
		Object data = aftManager.get(new ObjectId(aftId));
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/query")
	public JSONMessage query(@ModelAttribute AuditionFTExample example) {
		Object data = aftManager.query(ReqUtil.getUserId(), example);
		return JSONMessage.success(null, data);
	}

	/** 答题 */
	@RequestMapping("/answer")
	public JSONMessage answer(@RequestParam String aftId, @RequestParam int examId, @RequestParam String answer,
			@RequestParam(defaultValue = "0") int score) {
		aftManager.answer(ReqUtil.getUserId(), ReqUtil.parseId(aftId), examId, com.mongodb.util.JSON.parse(answer), score);

		return JSONMessage.success();
	}

	/** 评分 */
	@RequestMapping("/score")
	public JSONMessage score(@RequestParam String aftId, @RequestParam int examId, @RequestParam int score) {
		aftManager.score(ReqUtil.getUserId(), ReqUtil.parseId(aftId), examId, score);
		return JSONMessage.success();
	}

}
