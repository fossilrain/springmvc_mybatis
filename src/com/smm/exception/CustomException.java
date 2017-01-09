package com.smm.exception;
/**
 * 
 * <p>Title: CustomException</p>
 * <p>Description:自定义异常 针对预期的异常抛出自定义异常 </p>
 * <p>Company: </p>
 * @author RainFossil
 * @date 2017年1月6日 上午9:10:21
 * @version 1.0.0
 */
public class CustomException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	public CustomException(String message){
		super(message);
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
