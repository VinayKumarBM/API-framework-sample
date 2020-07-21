package com.api.utils;

import java.util.Map;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class RestExecutor {
	private Response response;
	private RequestSpecification request;
	
	public RestExecutor(RequestSpecification request) {
		this.request = request;
	}
	
	/**
	 * Executes GET request and returns Response
	 * 
	 * @param path
	 * @return
	 */
	public Response get(String path) {		
		return get(path, null);
	}

	/**
	 * Executes GET request and returns Response
	 * 
	 * @param path
	 * @param headers
	 * @return Response
	 */
	public Response get(String path, Map<String, String> headers) {	
		if(headers != null) {
			request.headers(headers);
		}
		response = request.when().get(path);		
		Logs.info("\nResponse -->\n"+response.prettyPrint());		
		return response;			
	}

	/**
	 * Executes POST request and returns Response
	 * 
	 * @param path
	 * @param body
	 * @param contentType
	 * @return Response
	 */
	public Response post(String path, String bodyContent) {
		return post(path, null, bodyContent);
	}

	/**
	 * Executes POST request and returns Response
	 * 
	 * @param path
	 * @param headers
	 * @param body
	 * @param contentType
	 * @return Response
	 */
	public Response post(String path, Map<String, String> headers, String bodyContent) {		
		if(headers != null) {
			request.headers(headers);
		}
		response = request.body(bodyContent).post(path);		
		Logs.info("\nResponse -->\n"+response.prettyPrint());
		return response;				
	}

	/**
	 * Executes PUT request and returns Response
	 * 
	 * @param path
	 * @param body
	 * @param contentType
	 * @return Response
	 */
	public Response put(String path, String bodyContent) {
		return put(path, null, bodyContent);
	}

	/**
	 * Executes PUT request and returns Response
	 * 
	 * @param path
	 * @param headers
	 * @param body
	 * @param contentType
	 * @return Response
	 */
	public Response put(String path, Map<String, String> headers, String bodyContent) {		
		if(headers != null) {
			request.headers(headers);
		}
		response = request.body(bodyContent).put(path);		
		Logs.info("\nResponse -->\n"+response.prettyPrint());
		return response;				
	}
	
	/**
	 * Executes DELETE request and returns Response
	 * 
	 * @param path
	 * @param headers
	 * @return
	 */
	public Response delete(String path, Map<String, String> headers) {				
		if(headers != null) {
			request.headers(headers);
		}
		response = request.delete(path);		
		Logs.info("\nResponse -->\n"+response.prettyPrint());
		return response;				
	}

	/**
	 * Executes DELETE request and returns Response
	 * 
	 * @param path
	 * @return
	 */
	public Response delete(String path) {		
		return delete(path, null);				
	}

}
