package com.smm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * <p>Title: LoginController</p>
 * <p>Description: 登录controller</p>
 * <p>Company: </p>
 * @author RainFossil
 * @date 2017年1月9日 上午10:44:32
 * @version 1.0.0
 */
@Controller
public class LoginController {

	//登录
	@RequestMapping("/login")
	public String login(HttpSession session,String username,String password) throws Exception{
		//调用service进行身份验证
		//。。。
		//在session中存储用户信息
		session.setAttribute("username", username);
		//重定向到商品列表页面
		return "redirect:/item/queryItems.action";
	}
	//退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception{
		//清楚session信息
		session.invalidate();
		//重定向到商品列表页面
		return "redirect:/item/queryItems.action";
	}
}
