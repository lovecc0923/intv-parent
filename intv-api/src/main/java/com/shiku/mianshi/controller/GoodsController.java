package com.shiku.mianshi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xyz.commons.utils.ReqUtil;
import cn.xyz.commons.vo.JSONMessage;
import cn.xyz.mianshi.service.GoodsManager;

import com.alibaba.fastjson.JSON;

@RestController
public class GoodsController {

	@Autowired
	private GoodsManager goodsManager;

	@RequestMapping("/biz_goods/buy")
	public JSONMessage buyBizGoods(@RequestParam int goodsId, @RequestParam(defaultValue = "1") int count) {
		Object data = goodsManager.buyBizGoods(ReqUtil.getUserId(), goodsId, count);
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/pay_goods/buy")
	public JSONMessage buyPayGoods(@RequestParam int rechargeType, @RequestParam int goodsId, @RequestParam(defaultValue = "1") int count) {
		Object data = goodsManager.buyPayGoods(ReqUtil.getUserId(), rechargeType, goodsId, count);
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/exam_goods/buy")
	public JSONMessage buyExamGoods(@RequestParam String text) {
		Object data = goodsManager.buyExamGoods(ReqUtil.getUserId(), JSON.parseArray(text, Integer.class));
		return JSONMessage.success(null, data);
	}

	@RequestMapping("/{catName}/list")
	public JSONMessage selectGoods(@PathVariable String catName) {
		Object data;
		if ("biz_goods".equals(catName))
			data = goodsManager.selectBizGoods();
		else if ("pay_goods".equals(catName))
			data = goodsManager.selectPayGoods();
		else if ("exam_goods".equals(catName))
			data = goodsManager.selectGoodsByCategoryId(4, ReqUtil.getUserId());
		else
			data = null;

		return JSONMessage.success(null, data);
	}

}
