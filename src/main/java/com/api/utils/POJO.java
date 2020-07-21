package com.api.utils;

import com.google.gson.Gson;

import io.restassured.response.Response;

public class POJO {	 
	
	public static <T> T fromJSON(Response response, Class T) {		
		Gson gson = new Gson();
		T pojo = (T) gson.fromJson(response.asString(), T);
		//System.out.println("Response Details:\n"+pojo);
		return pojo;
	}
}
