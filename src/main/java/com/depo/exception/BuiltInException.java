package com.depo.exception;

import org.springframework.http.HttpStatus;

public class BuiltInException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public BuiltInException(String message) {
		super(message);
	}
	
    public HttpStatus getHttpStatus() {// 500 yerine 403 dönmesi için
        return HttpStatus.FORBIDDEN;
    }
}