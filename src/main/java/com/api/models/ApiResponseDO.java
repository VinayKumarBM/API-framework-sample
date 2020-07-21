package com.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponseDO {

	@SerializedName("code")
	@Expose
	private Long code;
	@SerializedName("type")
	@Expose
	private String type;
	@SerializedName("message")
	@Expose
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