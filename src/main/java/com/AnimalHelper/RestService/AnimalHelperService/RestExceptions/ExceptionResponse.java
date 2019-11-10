package com.AnimalHelper.RestService.AnimalHelperService.RestExceptions;

import java.util.Date;

/*
 * This is a template for all the exceptions messages in this application
 */
public class ExceptionResponse {

	private Date timestamp;
	private String error;
	private String details;
	
	
	
	public ExceptionResponse(Date timestamp, String error, String details) {
		super();
		this.timestamp = timestamp;
		this.error = error;
		this.details = details;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public String getError() {
		return error;
	}
	public String getDetails() {
		return details;
	}
	
	
}
