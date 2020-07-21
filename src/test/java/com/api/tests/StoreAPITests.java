package com.api.tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.api.models.ApiResponseDO;
import com.api.models.InventoryDO;
import com.api.models.OrderDO;
import com.api.utils.JSONPayload;
import com.api.utils.Logs;
import com.api.utils.POJO;

public class StoreAPITests extends TestBase {
	private final Long ORDER_ID = 341809465L;
	private final Long QUANTITY = 2L;
	private final String ORDER_STATUS = "dispatched";
	private final Boolean COMPLETE = true;
	private final Long PET_ID = 71171L;
	
	@Test (description = "Place an Order for a Pet")
	public void TC001_PlaceOrder() {
		JSONObject order = JSONPayload.orderJSON(ORDER_ID, PET_ID, QUANTITY, ORDER_STATUS, COMPLETE);
		Logs.info("Pet Payload: \n"+order.toJSONString());
		
		response = restExecutor.post("/store/order", order.toJSONString());
		restValidator.validateStatus(response, 200);
		OrderDO orderDO = POJO.fromJSON(response, OrderDO.class);
		restValidator.validateResponseMatches(orderDO.getId(), ORDER_ID);
		restValidator.validateResponseMatches(orderDO.getStatus(), ORDER_STATUS);
		restValidator.validateResponseMatches(orderDO.getPetId(), PET_ID);
		restValidator.validateResponseMatches(orderDO.getQuantity(), QUANTITY);
		restValidator.validateResponseMatches(orderDO.getComplete(), COMPLETE);
	}
	
	@Test (description = "Retrieve purchase Order by order ID")
	public void TC002_RetrieveOrder() {
		response = restExecutor.get(String.format("/store/order/%s", ORDER_ID));
		restValidator.validateStatus(response, 200);
		OrderDO orderDO = POJO.fromJSON(response, OrderDO.class);
		restValidator.validateResponseMatches(orderDO.getId(), ORDER_ID);
		restValidator.validateResponseMatches(orderDO.getStatus(), ORDER_STATUS);
		restValidator.validateResponseMatches(orderDO.getPetId(), PET_ID);
		restValidator.validateResponseMatches(orderDO.getQuantity(), QUANTITY);
		restValidator.validateResponseMatches(orderDO.getComplete(), COMPLETE);
	}
	
	@Test (description = "Retrieve store Inventory")
	public void TC003_RetrieveInventory() {
		response = restExecutor.get("/store/inventory");
		restValidator.validateStatus(response, 200);
		InventoryDO inventoryDO = POJO.fromJSON(response, InventoryDO.class);
		restValidator.validateResponseHasValue(inventoryDO.getAvailable());
		restValidator.validateResponseHasValue(inventoryDO.getPending());
		restValidator.validateResponseHasValue(inventoryDO.getSold());
	}
	
	@Test (description = "Delete a Order in store") 
	public void TC004_DeleteOrderInStore() {
		response = restExecutor.delete(String.format("/store/order/%s", ORDER_ID));
		restValidator.validateStatus(response, 200);
		ApiResponseDO responseDO = POJO.fromJSON(response, ApiResponseDO.class);
		restValidator.validateResponseMatches(responseDO.getMessage(), String.valueOf(ORDER_ID));
	}
	
	@Test (description = "Retrieve purchase Order by invalid order ID") 
	public void TC005_RetrieveOrderInvalidID() {
		response = restExecutor.get(String.format("/store/order/%s", ORDER_ID));
		restValidator.validateStatus(response, 404);
		ApiResponseDO responseDO = POJO.fromJSON(response, ApiResponseDO.class);
		restValidator.validateResponseMatches(responseDO.getMessage(), "Order not found");
	}
}
