package com.api.tests;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.api.models.ApiResponseDO;
import com.api.models.PetsDO;
import com.api.utils.JSONPayload;
import com.api.utils.Logs;
import com.api.utils.POJO;

public class PetsAPITests extends TestBase {
	private final Long PET_ID = 71171L;
	private final String PET_NAME = "TOM";
	private final String PET_STATUS = "available";
	
	@Test (description = "Retrieve pets with status as available") 
	public void TC001_RetrieveAvailablePets() {
		List<PetsDO> petsDO = getPetByStatus("available");
		restValidator.validateResponseMatches(petsDO.get(0).getStatus(), "available");
	}

	@Test (description = "Retrieve pets with status as pending") 
	public void TC002_RetrievePendingPets() {
		List<PetsDO> petsDO = getPetByStatus("pending");
		restValidator.validateResponseMatches(petsDO.get(0).getStatus(), "pending");
	}

	@Test (description = "Retrieve pets with status as sold") 
	public void TC003_RetrieveSoldPets() {
		List<PetsDO> petsDO = getPetByStatus("sold");
		restValidator.validateResponseMatches(petsDO.get(0).getStatus(), "sold");
	}

	@Test (description = "Retrieve pets with status as Invalid") 
	public void TC004_RetrieveInvalidPets() {
		List<PetsDO> petsDO = getPetByStatus("Invalid");
		restValidator.validateResponseMatches(petsDO.isEmpty(), true);
	}

	private List<PetsDO> getPetByStatus(String status) {
		response = restExecutor.get(String.format("/pet/findByStatus?status=%s",status));
		restValidator.validateStatus(response, 200);
		PetsDO[] petsDO = POJO.fromJSON(response, PetsDO[].class);
		Logs.info("Response Size: "+petsDO.length);		
		return Arrays.asList(petsDO);
	}

	@Test (description = "Add a pet to store") 
	public void TC005_AddPetToStore() {
		JSONObject category = JSONPayload.getTagCategoryJSONObject(fake.random().nextLong(1000L), fake.cat().breed());
		JSONObject tag1 = JSONPayload.getTagCategoryJSONObject(fake.random().nextLong(1000L), fake.cat().registry());		
		JSONObject tag2 = JSONPayload.getTagCategoryJSONObject(fake.random().nextLong(1000L), fake.cat().registry());		
		JSONArray tags = JSONPayload.getJSONArray(tag1, tag2);		
		JSONArray photoUrls = JSONPayload.getJSONArray(fake.internet().url(),fake.internet().url(),fake.internet().url());
		JSONObject pet = JSONPayload.petJSON(PET_ID, category, PET_NAME, photoUrls, tags, PET_STATUS);
		Logs.info("Pet Payload: \n"+pet.toJSONString());
		
		response = restExecutor.post("/pet", pet.toJSONString());
		restValidator.validateStatus(response, 200);
		PetsDO petsDO = POJO.fromJSON(response, PetsDO.class);
		restValidator.validateResponseMatches(petsDO.getId(), PET_ID);
		restValidator.validateResponseMatches(petsDO.getStatus(), PET_STATUS);
		restValidator.validateResponseMatches(petsDO.getName(), PET_NAME);
	}
	
	@Test (description = "Retrieve a pet from store") 
	public void TC006_RetrievePetFromStore() {
		response = restExecutor.get(String.format("/pet/%s", PET_ID));
		restValidator.validateStatus(response, 200);
		PetsDO petsDO = POJO.fromJSON(response, PetsDO.class);
		restValidator.validateResponseMatches(petsDO.getId(), PET_ID);
		restValidator.validateResponseMatches(petsDO.getStatus(), PET_STATUS);
		restValidator.validateResponseMatches(petsDO.getName(), PET_NAME);
	}
	
	@Test (description = "Update a pet name & status in store") 
	public void TC007_UpdatePetNameAndStatus() {
		String name = fake.cat().name();
		String status = fake.options().nextElement(new String[] {"available", "pending", "sold"}).intern();
		response = request.contentType("application/x-www-form-urlencoded")
			.formParam("name", name)
			.formParam("status", status).log().all()
			.when()
			.post(String.format("/pet/%s", PET_ID));

		restValidator.validateStatus(response, 200);
		ApiResponseDO responseDO = POJO.fromJSON(response, ApiResponseDO.class);
		restValidator.validateResponseMatches(responseDO.getMessage(), String.valueOf(PET_ID));
	}
	
	@Test (description = "Update a pet in store") 
	public void TC008_UpdatePetInStore() {
		JSONObject category = JSONPayload.getTagCategoryJSONObject(fake.random().nextLong(1000L), fake.cat().breed());
		JSONObject tag1 = JSONPayload.getTagCategoryJSONObject(fake.random().nextLong(1000L), fake.cat().registry());	
		JSONArray tags = JSONPayload.getJSONArray(tag1);		
		JSONArray photoUrls = JSONPayload.getJSONArray(fake.internet().url());
		JSONObject pet = JSONPayload.petJSON(PET_ID, category, PET_NAME, photoUrls, tags, PET_STATUS);
		Logs.info("Pet Payload: \n"+pet.toJSONString());
		
		response = restExecutor.put("/pet", pet.toJSONString());
		restValidator.validateStatus(response, 200);
		PetsDO petsDO = POJO.fromJSON(response, PetsDO.class);
		restValidator.validateResponseMatches(petsDO.getId(), PET_ID);
		restValidator.validateResponseMatches(petsDO.getStatus(), PET_STATUS);
		restValidator.validateResponseMatches(petsDO.getName(), PET_NAME);
	}
	
	@Test (description = "Delete a pet in store") 
	public void TC009_DeletePetInStore() {
		Map<String,String> header = new HashMap<String,String>();
		header.put("api_key", "special-key");
		response = restExecutor.delete(String.format("/pet/%s", PET_ID), header);
		restValidator.validateStatus(response, 200);
		ApiResponseDO responseDO = POJO.fromJSON(response, ApiResponseDO.class);
		restValidator.validateResponseMatches(responseDO.getMessage(), String.valueOf(PET_ID));
	}
	
	@Test (description = "Retrieve Pet by invalid pet ID") 
	public void TC010_RetrievePetInvalidID() {
		response = restExecutor.get(String.format("/pet/%s", PET_ID));
		restValidator.validateStatus(response, 404);
		ApiResponseDO responseDO = POJO.fromJSON(response, ApiResponseDO.class);
		restValidator.validateResponseMatches(responseDO.getMessage(), "Pet not found");
	}
}
