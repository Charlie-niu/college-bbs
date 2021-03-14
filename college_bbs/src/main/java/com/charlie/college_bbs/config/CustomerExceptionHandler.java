package com.charlie.college_bbs.config;

import com.charlie.college_bbs.utils.CustomerException;
import com.charlie.college_bbs.utils.Message;
import com.charlie.college_bbs.utils.MessageUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @description: 统一异常处理类
 **/
@RestControllerAdvice
public class CustomerExceptionHandler {
	
	@ExceptionHandler(value =  Exception.class) // 捕获 Controller 中抛出的指定类型的异常，也可以指定其他异常
	public <E> Message handler(Exception exception){
	    exception.printStackTrace();
	    if(exception instanceof CustomerException) {
	    	return MessageUtil.error(exception.getMessage());
	    }
	    return MessageUtil.error("后台接口异常");
	}
}
