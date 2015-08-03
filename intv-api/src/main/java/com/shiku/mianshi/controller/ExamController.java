package com.shiku.mianshi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xyz.commons.vo.JSONMessage;
import cn.xyz.mianshi.service.ExamManager;
import cn.xyz.mianshi.vo.ExamVO;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping("/exam")
public class ExamController {

	@Autowired
	private ExamManager examManager;

	@RequestMapping("/get")
	public JSONMessage getExam(@RequestParam int examId) {
		ExamVO data = examManager.get(examId, true);

		return JSONMessage.success(null, data);
	}

	/** 获取题库 */
	@RequestMapping("/gets")
	public JSONMessage getExamList(@RequestParam String text) {
		List<Integer> examIdList = JSON.parseArray(text, Integer.class);
		List<ExamVO> data = examManager.selectByIdList(examIdList, true);
		return JSONMessage.success(null, data);
	}

}
