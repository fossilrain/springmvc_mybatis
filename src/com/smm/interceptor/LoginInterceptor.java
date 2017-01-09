package com.smm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
 * <p>Title: LoginInterceptor</p>
 * <p>Description: 登录校验拦截器</p>
 * <p>Company: </p>
 * @author RainFossil
 * @date 2017年1月9日 上午10:58:13
 * @version 1.0.0
 */
public class LoginInterceptor implements HandlerInterceptor{
	//进入handler方法之前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取请求的url
		String url = request.getRequestURI();
		//判断url是否是公开地址，实际使用时将公开地址配置在配置文件中
		//这里公开地址是登录提交的地址
		if(url.indexOf("login.action") > -1){
			return true;
		}
		//从session中获取用户信息
		HttpSession session =  request.getSession();
		String userName = (String) session.getAttribute("username");
		if(userName != null){//身份信息存在，放行
			return true;
		}
		//执行到这里不放行,转发到登录页面
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		//return false 标示拦截，不向下执行
		//return true 标示放行，
		return false;
	}
	//进入handler方法之后，返回ModelAndView之前
	//应用场景从ModelAndView出发，将公用的模型数据（如菜单导航）在这里传到视图，也可以在这里统一指定视图
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("HandlerInterceptor1...postHandle");
	}
	//执行handler完成后执行此方法
	//应用场景：统一异常处理，统一日志处理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("HandlerInterceptor1...afterCompletion");		
	}
}
