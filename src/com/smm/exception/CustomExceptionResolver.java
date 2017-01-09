package com.smm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * <p>Title: CustomExceptionResolver</p>
 * <p>Description: 全局异常处理器</p>
 * <p>Company: </p>
 * @author RainFossil
 * @date 2017年1月6日 上午9:18:39
 * @version 1.0.0
 */
public class CustomExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//handler 就是处理器适配器要执行的handler对象（只有一个method）
		
		//解析出异常类型
		//如果该异常是系统自定义类型，直接取出异常信息在页面显示
		//如果该异常不是系统自定义类型，构造一个自定义异常（信息为未知错误）
		CustomException ce = null;
		if(ex instanceof CustomException){
			ce = (CustomException)ex;
		}else{
			ce = new CustomException("未知错误！");
		}
		//错误信息
		String message = ce.getMessage();
		
		ModelAndView modelAndView = new ModelAndView();
		//将错误信息传到页面
		modelAndView.addObject("message", message);
		//指向错误页面
		modelAndView.setViewName("error.jsp");
		return modelAndView;
	}

}
