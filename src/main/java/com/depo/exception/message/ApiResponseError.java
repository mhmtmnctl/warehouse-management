package com.depo.exception.message;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiResponseError {

	private HttpStatus status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime timeStamp;
	
	private String message;
	// Request edilen end-pointi tutmak icin
	private String requestURI;

	private ApiResponseError() {
		timeStamp = LocalDateTime.now();
	}

	public ApiResponseError(HttpStatus status) {
		this();
		this.message = "Unexpected Error";
		this.status = status;
	}

	public ApiResponseError(HttpStatus status, String message, String requestURI) {
		this(status);
		this.message = message;
		this.requestURI = requestURI;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

}
