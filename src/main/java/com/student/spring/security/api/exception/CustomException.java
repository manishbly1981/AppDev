package com.student.spring.security.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.METHOD_FAILURE)
public class CustomException extends RuntimeException{
	public CustomException() {
		super("Undefined Exception");
	}
}
