package com.serviceApp.utility.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

	private String statusCode; // create status integer for user response
	private String message; // create message in String for give the user message
	private Object object;
	// private Object data;

	public Response(String message, String statusCode) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public Response(String message, String statusCode, Object object) {
		this.statusCode = statusCode;
		this.message = message;
		this.object = object;
	}

	@Override
	public String toString() {
		return "Response [statusCode=" + statusCode + ", message=" + message + ", object=" + object + "]";
	}

}
