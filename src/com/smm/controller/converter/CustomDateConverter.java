package com.smm.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class CustomDateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		//实现将字符串转换成日期（格式为yyyy-MM-dd HH:mm:ss）
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(source != null && !"".equals(source)){
			try {
				return sdf.parse(source);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//转换不成功返回null;
		return null;
	}

}
