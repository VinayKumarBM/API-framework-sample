package com.api.utils;

import static org.testng.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import io.restassured.response.Response;

public class RestValidator {

	public void validateStatus(Response response, int statusCode) {
		Assert.assertEquals(response.getStatusCode(), statusCode, "Status code did not match");
	}
	
	public void validateHeaders(Response response, Map<String, String> headers) {
		for(Map.Entry<String, String> header : headers.entrySet()) {
			assertEquals(response.getHeader(header.getKey()), header.getValue(),"Header value is not correct");
		}		
	}

	public void validateContentType(Response response, String contentType) {		
		assertEquals(response.getContentType(),contentType, "contentType value is not correct");	
	}

	public void validateHeader(Response response, String headerName, String headerValue) {
		assertEquals(response.getHeader(headerName), headerValue,"Header value is not correct");		
	}

	public void validateIfBodyHasItems(Response response, String path, List expectedList) {
		List<Object> valueList = null;
		try {
			valueList=response.then().extract().path(path);
			Logs.info("Values found: "+valueList);
		}
		catch(ClassCastException exp) {
			Logs.info("Exception Occured: "+exp.getMessage());
			fail("There was issue with the request parameter");
		}
		if(valueList!=null)
			assertTrue(valueList.containsAll(expectedList),"All the expected values were not present");		
	}

	public void validateIfBodyHas(Response response, String path, Object expectedValue) {
		Object value = null;
		try {
			value=response.then().extract().path(path);
			Logs.info("Value found: "+value);
		}
		catch(ClassCastException exp) {
			Logs.info("Exception Occured: "+exp.getMessage());
			fail("There was issue with the request parameter");
		}
		if(value!=null)
			assertTrue(value.equals(expectedValue),"The expected value was not present");		
	}

	public void validateBody(Response response, String jsonBody) {
		assertTrue(response.getBody().asString().contains(jsonBody), "Response body did not contain the expected string value");
		
	}

	public void validateResponseTimeIsLessThan(Response response, Long timeInMilliSeconds) {
		Logs.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS));
		assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<=timeInMilliSeconds, "Response was greater than "+timeInMilliSeconds);		
	}

	public void validateJSONSize(Response response, String path,int jsonSize) {
		List<String> valueList = null;
		try {
			valueList=response.then().extract().path(path);
			Logs.info("Size: "+valueList.size());
		}
		catch(ClassCastException exp) {
			Logs.info("Exception Occured: "+exp.getMessage());
			fail("There was issue with the request parameter");
		}
		if(valueList!=null)
			assertEquals(valueList.size(), jsonSize, "Array Size is not equal");		
	}
	
	public void validateResponseContains(String actual, String expected) {
		assertTrue(actual.contains(expected), "Response did not contain expected <"+expected+
				"> in actual <"+actual+">");
	}
	
	public void validateResponseMatches(Object actual, Object expected) {
		assertEquals(actual, expected, " value did not match");
	}
	
	public void validateResponseHasValue(Object actual) {
		assertNotNull(actual, " response did not have any value");
	}
}
