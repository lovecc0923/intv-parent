package com.shiku.mianshi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xyz.commons.vo.JSONMessage;

@Controller
@RequestMapping("/talents")
public class TalentsController {

	@RequestMapping("/search")
	@ResponseBody
	public JSONMessage search() {
		JSONMessage jMessage = null;

		return jMessage;
	}

	@RequestMapping("/latest")
	@ResponseBody
	public JSONMessage latest() {
		JSONMessage jMessage = null;

		return jMessage;
	}

	@RequestMapping("/hot")
	@ResponseBody
	public JSONMessage hot() {
		JSONMessage jMessage = null;

		return jMessage;
	}

}
