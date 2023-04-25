package com.depo.exception;

public class BuiltInException extends RuntimeException {
	private static final long serialVersionUID = 1L;	
	public BuiltInException(String message) {
		super(message);
	}
}