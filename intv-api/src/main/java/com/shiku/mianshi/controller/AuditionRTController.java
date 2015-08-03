package com.shiku.mianshi.controller;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xyz.commons.utils.ReqUtil;
import cn.xyz.commons.vo.JSONMessage;
import cn.xyz.mianshi.example.AuditionRTExample;
import cn.xyz.mianshi.service.AuditionRTManager;

@RestController
@RequestMapping("/art")
public class AuditionRTController {
	@Autowired
	private AuditionRTManager auditionRTManager;

	/** 邀请复试 */
	@RequestMapping("/invite")
	public JSONMessage invite(HttpServletRequest request) {
		// AuditionInviteExample example = ReflectionUtils.parse(request,
		// AuditionInviteExample.class);
		return null;
	}

	/** 预约时间 */
	@RequestMapping("/reserveTime")
	public JSONMessage reserveTime(@RequestParam String artId, @RequestParam long startTime) {
		auditionRTManager.reserveTime(ReqUtil.getUserId(), ReqUtil.parseId(artId), startTime);
		return JSONMessage.success();
	}

	/** 开始会议 */
	@RequestMapping("/start")
	public JSONMessage start(@RequestParam String artId) {
		auditionRTManager.start(ReqUtil.getUserId(), ReqUtil.parseId(artId));
		return JSONMessage.success();
	}

	/** 个人同意复试邀请 */
	@RequestMapping("/agree")
	public JSONMessage agree(@RequestParam String text) {
		Object data = auditionRTManager.agree(ReqUtil.getUserId(), ReqUtil.parseArray(text));
		return JSONMessage.success(null, data);
	}

	/** 个人拒绝复试邀请 */
	@RequestMapping("/refuse")
	public JSONMessage refuse(@RequestParam String text) {
		Object data = auditionRTManager.refuse(ReqUtil.getUserId(), ReqUtil.parseArray(text));
		return JSONMessage.success(null, data);
	}

	/** 复试通过 */
	@RequestMapping("/pass")
	public JSONMessage pass(@RequestParam String text) {
		Object data = auditionRTManager.pass(ReqUtil.getUserId(), ReqUtil.parseArray(text));
		return JSONMessage.success(null, data);
	}

	/** 复试不通过 */
	@RequestMapping("/notpass")
	public JSONMessage notpass(@RequestParam String text) {
		Object data = auditionRTManager.notpass(ReqUtil.getUserId(), ReqUtil.parseArray(text));
		return JSONMessage.success(null, data);
	}

	/** 复试记录删除 */
	@RequestMapping("/delete")
	public JSONMessage delete(@RequestParam String text) {
		Object data = auditionRTManager.delete(ReqUtil.getUserId(), ReqUtil.parseArray(text));
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/get")
	public JSONMessage get(@RequestParam String artId) {
		Object data = auditionRTManager.get(new ObjectId(artId));
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/gets")
	public JSONMessage gets(@RequestParam String text) {
		Object data = auditionRTManager.gets(ReqUtil.parseArray(text));
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/query")
	public JSONMessage query(@ModelAttribute AuditionRTExample example) {
		// AuditionRTExample example = ReflectionUtils.parse(request,
		// AuditionRTExample.class);
		Object data = auditionRTManager.query(ReqUtil.getUserId(), example);
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/score")
	public JSONMessage score(@RequestParam String artId, @RequestParam int score) {
		auditionRTManager.score(ReqUtil.getUserId(), new ObjectId(artId), score);
		return JSONMessage.success();
	}

	// @RequestMapping("/v/score")
	// public JSONMessage vScore(@RequestParam String auditionId, @RequestParam
	// Integer score, @RequestParam(defaultValue = "0") int isClose) {
	// JSONMessage jMessage;
	//
	// try {
	// if (StringUtils.isEmpty(auditionId) || null == score) {
	// jMessage = Constants.Result.ParamsAuthFail;
	// } else {
	// auditionManager.vScore(auditionId, ReqUtils.getUserId(), score, isClose);
	//
	// jMessage = JSONMessage.getSuccess(null);
	// }
	// } catch (Exception e) {
	// logger.error("动态面试评分失败", e);
	//
	// jMessage = JSONMessage.wrapError(e);
	// }
	//
	// return jMessage;
	// }

	// private BasicDBObject createI(AuditionInviteParam param) {
	// BasicDBObject dbObj = new BasicDBObject();
	//
	// if (0 == param.getiExamId()) {
	// dbObj.put("stat", 0);
	// } else {
	// dbObj.put("stat", 1);// 静态面试状态：0=无；1=进行中；2=已完成；3=已打分
	// dbObj.put("examId", param.getiExamId());// 静态面试题库Id
	// dbObj.put("video", new BasicDBObject());
	// dbObj.put("image", new BasicDBObject());
	// dbObj.put("score", 0);// 静态面试得分
	// dbObj.put("scores", new Object[] {});// 静态面试得分列表
	// }
	//
	// return dbObj;
	// }
	//
	// private BasicDBObject createV(AuditionInviteParam param) {
	// BasicDBObject v = new BasicDBObject();
	//
	// if (1 == param.getIsVideo()) {
	// v.put("stat", 1);// 动态面试状态： 0=无；1=进行中；2=已完成；3=已打分
	// v.put("score", 0);// 动态面试得分
	// v.put("scores", new Object[] {});// 动态面试得分列表
	// } else {
	// v.put("stat", 0);
	// }
	//
	// return v;
	// }
	//
	// private BasicDBObject createW(AuditionInviteParam param) {
	// BasicDBObject dbObj = new BasicDBObject();
	//
	// if (0 == param.getwExamId()) {
	// dbObj.put("stat", 0);
	// } else {
	// dbObj.put("stat", 1);// 笔试状态：0=无；1=进行中；2=已完成；3=已打分
	// dbObj.put("examId", param.getwExamId());// 笔试题库id
	// dbObj.put("answer", new Object[] {});
	// dbObj.put("score", 0);// 笔试得分
	// }
	//
	// return dbObj;
	// }

	// @Override
	// public boolean vScore(String auditionId, int userId, int score, int
	// isClose) {
	// try {
	// DBObject obj = new BasicDBObject();
	// obj.put("userId", userId);
	// obj.put("score", score);
	// obj.put("time", Dates.currentTimeSeconds());
	//
	// BasicDBObject inc = new BasicDBObject();
	// inc.put("score", score);
	// inc.put("v.score", score);
	//
	// DBObject q = new BasicDBObject(MongoOperator.ID, new
	// ObjectId(auditionId));
	// DBObject o = new BasicDBObject();
	// o.put("$inc", inc);
	// o.put("$addToSet", new BasicDBObject("v.scores", new
	// BasicDBObject("$each", new Object[] { obj })));
	// o.put(MongoOperator.SET, new BasicDBObject("v.stat", 1 == isClose ? 3 :
	// 2));
	//
	// audition().update(q, o);
	//
	// return true;
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return false;
	// }

}
