package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.lang.reflect.Method;

import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.xml.XmlTest;

import com.api.utils.Logs;
import com.api.utils.RestExecutor;
import com.api.utils.RestValidator;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;
import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//	mvn clean test && mvn surefire-report:report-only && mvn site -DgenerateReports=false
@Listeners(ExtentITestListenerAdapter.class)
public class TestBase {
	RequestSpecification request;
	RestValidator restValidator;
	RestExecutor restExecutor;
	Response response;
	Faker fake;

	@BeforeTest
	public void logSetup(XmlTest test) {
		String filePath = "logs";
		File logFile = new File(filePath);
		if(!logFile.exists()) {
			logFile.mkdir();
		}
		
		ThreadContext.put("ROUTINGKEY", filePath+File.separator+test.getName());
	}
	
	@BeforeMethod
	public void setup(Method method) {
		request = given()
					.baseUri("https://petstore.swagger.io/v2")
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON);
		restExecutor = new RestExecutor(request);
		restValidator = new RestValidator();
		
		logDetails(method.getName(), "started");
		fake = new Faker();		
	}
	
	@AfterMethod
	public void teardown(ITestResult result) {
		String status = "PASSED";
		if(result.getStatus()==ITestResult.FAILURE) {
			status = "FAILED";			
		}
		logDetails(result.getName(), status);
	}
	
	private void logDetails(String testName, String message) {
		Logs.info("===============================================================================");
		Logs.info("\t\t\t"+String.format("%S - %S", testName, message)+"");
		Logs.info("===============================================================================");
	}
}
//	mvn clean test && mvn surefire-report:report-only && mvn site -DgenerateReports=false