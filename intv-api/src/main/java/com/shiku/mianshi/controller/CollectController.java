package com.shiku.mianshi.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xyz.commons.utils.ReqUtil;
import cn.xyz.commons.vo.JSONMessage;
import cn.xyz.mianshi.vo.UserCollect;
import cn.xyz.repository.CollectRepository;

@RestController
public class CollectController {

	@Autowired
	private CollectRepository collectRepository;

	@RequestMapping("/collect/add/{type}")
	public JSONMessage addCollect(@PathVariable("type") int type, @RequestParam String content, @RequestParam int tag) {
		UserCollect entity = new UserCollect();
		entity.setType(type);
		entity.setContent(content);
		// entity.setId(id);
		entity.setTag(tag);
		entity.setTime(System.currentTimeMillis() / 1000);
		entity.setUserId(ReqUtil.getUserId());

		Object data = collectRepository.add(entity);

		return JSONMessage.success(null, data);
	}

	@RequestMapping("/collect/delete")
	public JSONMessage deleteCollect(@RequestParam String collectId) {
		Object data = collectRepository.delete(new ObjectId(collectId));

		return JSONMessage.success(null, data);
	}

	@RequestMapping("/collect/list/{type}")
	public JSONMessage getCollecetList(@PathVariable("type") int type, @RequestParam(defaultValue = "") String collectId,
			@RequestParam(defaultValue = "10") int pageSize) {
		Object data = collectRepository.find(ReqUtil.getUserId(), type, ReqUtil.parseId(collectId), pageSize);

		return JSONMessage.success(null, data);
	}

}
