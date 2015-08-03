package com.shiku.mianshi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xyz.commons.utils.ReflectionUtils;
import cn.xyz.commons.utils.ReqUtil;
import cn.xyz.commons.vo.JSONMessage;
import cn.xyz.mianshi.example.JobExample;
import cn.xyz.mianshi.service.JobManager;

@RestController
public class JobController {

	@Autowired
	private JobManager jobManager;

	/** 发布职位 */
	@RequestMapping("/job/add")
	public JSONMessage save(HttpServletRequest request) {
		JobExample example = ReflectionUtils.parse(request, JobExample.class);
		Object data = jobManager.save(ReqUtil.getUserId(), example);
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/job/republish")
	public JSONMessage republish(HttpServletRequest request) {
		JobExample example = ReflectionUtils.parse(request, JobExample.class);
		Object data = jobManager.republish(ReqUtil.getUserId(), example);
		return JSONMessage.success(null, data);
	}

	/** 删除职位 */
	@RequestMapping("/job/delete")
	public JSONMessage delete(@RequestParam String text) {
		Object data = jobManager.delete(ReqUtil.getUserId(), ReqUtil.parseArray(text));
		return JSONMessage.success(null, data);
	}

	/** 更新职位 */
	@RequestMapping("/job/update")
	public JSONMessage updateOne(HttpServletRequest request) {
		JobExample example = ReflectionUtils.parse(request, JobExample.class);
		Object data = jobManager.updateOne(ReqUtil.getUserId(), example);
		return JSONMessage.success(null, data);
	}

	/** 刷新职位：修改刷新时间 */
	@RequestMapping("/job/refresh")
	public JSONMessage updateMulti(@RequestParam String text) {
		jobManager.updateMulti(ReqUtil.getUserId(), ReqUtil.parseArray(text), System.currentTimeMillis() / 1000);
		return JSONMessage.success();
	}

	/** 暂停职位、恢复职位、取消发布：修改职位状态、1=正常；2=暂停发布；3=取消发布 */
	@RequestMapping("/job/status/{status}")
	public JSONMessage updateMulti(@RequestParam String text, @PathVariable int status) {
		Object data = jobManager.updateMulti(ReqUtil.getUserId(), ReqUtil.parseArray(text), status);
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/job/get")
	public JSONMessage get(@RequestParam String jobId) {
		Object data = jobManager.selectById(ReqUtil.parseId(jobId));
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/job/list")
	public JSONMessage getJobList(@RequestParam(defaultValue = "0") Integer status, @RequestParam(defaultValue = "0") Integer pageIndex,
			@RequestParam(defaultValue = "20") Integer pageSize) {
		Object data = jobManager.selectByUserId(ReqUtil.getUserId(), status, pageIndex, pageSize);
		return JSONMessage.success(null, data);
	}

	/** 职位搜索 */
	@RequestMapping("/job/query")
	public JSONMessage query(@ModelAttribute JobExample example, @RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "20") int pageSize) {
		Object data = jobManager.query(example, pageIndex, pageSize);
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/job/follow_list")
	public JSONMessage selectByCompanyId(@RequestParam(defaultValue = "0") Integer pageIndex,
			@RequestParam(defaultValue = "20") Integer pageSize) {
		Object data = jobManager.selectByCompanyId(ReqUtil.getUserId(), pageIndex, pageSize);
		return JSONMessage.success(null, data);
	}

	/** 最热职位列表 */
	@RequestMapping(value = "/job/hot")
	public JSONMessage selectOrderByActive(@RequestParam(defaultValue = "0") Integer pageIndex,
			@RequestParam(defaultValue = "20") Integer pageSize) {
		Object data = jobManager.selectOrderByActive(pageIndex, pageSize);
		return JSONMessage.success(null, data);
	}

	/** 最新职位列表 */
	@RequestMapping(value = "/job/latest")
	public JSONMessage selectOrderByTime(@RequestParam(defaultValue = "0") Integer pageIndex,
			@RequestParam(defaultValue = "20") Integer pageSize) {
		Object data = jobManager.selectOrderByTime(pageIndex, pageSize);
		return JSONMessage.success(null, data);
	}

}
