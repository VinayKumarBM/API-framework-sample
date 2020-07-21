package com.api.utils;

import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONPayload {

	@SuppressWarnings("unchecked")
	public static JSONObject userJSON(Long id, String userName, String firstName, String lastName,
			String email, String password, String phone, Integer userStatus) {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("username", userName);
		json.put("firstName", firstName);
		json.put("lastName", lastName);
		json.put("email", email);
		json.put("password", password);
		json.put("phone", phone);
		json.put("userStatus", userStatus);		
		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONArray getJSONArray(Object... tags) {
		JSONArray jsonArray = new JSONArray();
		for(Object tag : tags) {
			jsonArray.add(tag);
		}
		return jsonArray;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject getTagCategoryJSONObject(Long id, String name) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", id);
		jsonObject.put("name", name);
		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject petJSON(Long id, JSONObject category, String name, JSONArray photoUrls, JSONArray tags, String status) {
		JSONObject pet = new JSONObject();
		pet.put("id", id);
		pet.put("category", category);
		pet.put("name", name);
		pet.put("photoUrls", photoUrls);
		pet.put("tags", tags);
		pet.put("status", status);
		return pet;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject orderJSON(Long orderID, Long petID, Long quantity, String status, Boolean complete) {
		JSONObject json = new JSONObject();
		json.put("id", orderID);
		json.put("petId", petID);
		json.put("quantity", quantity);
		json.put("shipDate", new Date().toInstant().toString());
		json.put("status", status);
		json.put("complete", complete);
		return json;
	}
}
