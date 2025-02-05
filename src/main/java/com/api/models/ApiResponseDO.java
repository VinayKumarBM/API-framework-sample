package com.api.models;

public class ApiResponseDO {

	private Long code;
	private String type;
	private String message;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "[\ncode: "+ code +",\ntype: "+ type +",\nmessage: "+ message +"\n]";
	}

}