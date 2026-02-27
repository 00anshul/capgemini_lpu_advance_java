package com.spring_prac;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationException {
	@ExceptionHandler(Exception.class)
	public String handleException() {
		return "Exception handle method";
	}
}
