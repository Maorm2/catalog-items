package com.example.items.catalogitems.exception;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExceptionResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private Date timestamp;
	private String messgae;
	private String details;

	public ExceptionResponse(Date timestamp, String messgae, String details) {
		super();
		this.timestamp = timestamp;
		this.messgae = messgae;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessgae() {
		return messgae;
	}

	public String getDetails() {
		return details;
	}

}
