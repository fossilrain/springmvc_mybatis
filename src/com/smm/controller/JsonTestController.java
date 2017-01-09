package com.smm.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.smm.po.ItemCustom;

/**
 * 
 * <p>Title: JsonTestController</p>
 * <p>Description:json交互测试 </p>
 * <p>Company: </p>
 * @author RainFossil
 * @date 2017年1月6日 下午2:26:44
 * @version 1.0.0
 */
@Controller
public class JsonTestController {

	//@RequestBody 将请求的商品信息的json串转换成商品itemCustom对象
	//@ResponseBody 将itemCustom转换成json输出
	@RequestMapping("/requestJson")
	public @ResponseBody ItemCustom requestJson(@RequestBody ItemCustom itemCustom){
		
		return itemCustom;
	}
	@RequestMapping("/requestKeyVal")
	public @ResponseBody ItemCustom requestKeyVal(ItemCustom itemCustom){
		
		return itemCustom;
	}
	//map测试 想复制到map中，此法不行
	@RequestMapping("/requestJsonMap")
	public @ResponseBody Map requestJsonMap(@RequestBody Map map){
		
		return map;
	}
}
