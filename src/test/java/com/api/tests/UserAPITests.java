package com.api.tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.api.models.ApiResponseDO;
import com.api.models.UserDO;
import com.api.utils.JSONPayload;
import com.api.utils.Logs;
import com.api.utils.POJO;

public class UserAPITests extends TestBase {
	private final Long USER_ID = 341809465L;
	private final String USER_NAME = "john.doe";
	private final String PASSWORD = "jo34d03";
	
	@Test (description = "Verify creation of new User") 
	public void TC001_CreateUserAPI() {
		JSONObject json = JSONPayload.userJSON(USER_ID, USER_NAME, fake.name().firstName(), fake.name().lastName(), 
				fake.internet().emailAddress(USER_NAME), PASSWORD, fake.phoneNumber().phoneNumber(), fake.random().nextInt(999));		
		Logs.info("User Payload:\n"+json.toJSONString());
		response = restExecutor.post("/user", json.toJSONString());
		restValidator.validateStatus(response, 200);
		ApiResponseDO apiResponseDO = POJO.fromJSON(response, ApiResponseDO.class);
		
		Logs.info("Message "+apiResponseDO.getMessage());
		restValidator.validateResponseMatches(apiResponseDO.getMessage(), String.valueOf(USER_ID));
	}
		
	@Test (description = "Login with newly created user") 
	public void TC002_UserLoginAPI() {
		response = restExecutor.get(String.format("/user/login?username=%s&password=%s", USER_NAME, PASSWORD));
		restValidator.validateStatus(response, 200);
		ApiResponseDO apiResponseDO = POJO.fromJSON(response, ApiResponseDO.class);
		
		Logs.info("Message: "+apiResponseDO.getMessage());
		restValidator.validateResponseContains(apiResponseDO.getMessage(), "logged in user session:");		
	}
	
	@Test (description = "Retrieve newly created user") 
	public void TC003_RetrieveUserAPI() {
		response = restExecutor.get(String.format("/user/%s",USER_NAME));
		restValidator.validateStatus(response, 200);
		UserDO userDO = POJO.fromJSON(response, UserDO.class);
		
		Logs.info("ID: "+userDO.getId());
	}
	
	@Test (description = "Update newly created user") 
	public void TC004_UpdateUserAPI() {
		JSONObject json  = JSONPayload.userJSON(USER_ID, USER_NAME, fake.name().firstName(), fake.name().lastName(), 
				fake.internet().emailAddress(USER_NAME), PASSWORD, fake.phoneNumber().phoneNumber(), fake.random().nextInt(999));
		Logs.info("User Payload:\n"+json.toJSONString());
		response = restExecutor.put(String.format("/user/%s",USER_NAME), json.toJSONString());
		restValidator.validateStatus(response, 200);
		ApiResponseDO apiResponseDO = POJO.fromJSON(response, ApiResponseDO.class);
		
		Logs.info("Message: "+apiResponseDO.getMessage());
		restValidator.validateIfBodyHas(response, "message", String.valueOf(USER_ID));			
	}

	@Test (description = "Verify user is able to logout of store") 
	public void TC005_UserLogoutAPI() {
		response = restExecutor.get("/user/logout");
		restValidator.validateStatus(response, 200);
		ApiResponseDO apiResponseDO = POJO.fromJSON(response, ApiResponseDO.class);
		
		Logs.info("Message: "+apiResponseDO.getMessage());
		restValidator.validateResponseMatches(apiResponseDO.getMessage(), "ok");		
	}
	
	@Test (description = "Delete newly created user") 
	public void TC006_DeleteUserAPI() {
		response = restExecutor.delete(String.format("/user/%s", USER_NAME));
		restValidator.validateStatus(response, 200);
		ApiResponseDO apiResponseDO = POJO.fromJSON(response, ApiResponseDO.class);
		
		Logs.info("Message: "+apiResponseDO.getMessage());
		restValidator.validateResponseMatches(apiResponseDO.getMessage(), USER_NAME);		
	}
	
	@SuppressWarnings("unchecked")
	@Test (description = "Verify creation of multiple users") 
	public void TC007_CreateMultipleUsersAPI() {
		String user = fake.name().username();
		JSONArray userArrayJSON = new JSONArray();		
		JSONObject userJSON1 = JSONPayload.userJSON(USER_ID+2, user, fake.name().firstName(), fake.name().lastName(), 
				fake.internet().emailAddress(user), PASSWORD, fake.phoneNumber().phoneNumber(), fake.random().nextInt(999));
		
		user = fake.name().username();
		JSONObject userJSON2 = JSONPayload.userJSON(USER_ID+3, user, fake.name().firstName(), fake.name().lastName(), 
				fake.internet().emailAddress(user), PASSWORD, fake.phoneNumber().phoneNumber(), fake.random().nextInt(999));
		
		userArrayJSON.add(userJSON1);
		userArrayJSON.add(userJSON2);
		Logs.info(userArrayJSON.toJSONString());
		response = restExecutor.post("/user/createWithArray", userArrayJSON.toJSONString());
		restValidator.validateStatus(response, 200);
		ApiResponseDO apiResponseDO = POJO.fromJSON(response, ApiResponseDO.class);
		
		Logs.info("Message "+apiResponseDO.getMessage());
		restValidator.validateResponseMatches(apiResponseDO.getMessage(), "ok");
	}
}
