package com.douglas.api.jointly.controler;

import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonProperty;

@ResponseBody
public class APIResponse {

	@JsonProperty
	private boolean error;
	@JsonProperty
	private String message;
	@JsonProperty
	private Object data;
	
	 public APIResponse() {
	 }

	public APIResponse(boolean error, String message, Object data) {
		super();
		this.error = error;
		this.message = message;
		this.data = data;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
