package com.api.utils;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ReportManager {
	private static ExtentReports extent;
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	private static String basePath = System.getProperty("user.dir");
	private static String extentConfigFilePath = basePath+"/html-config.xml";

	//Create an extent report instance
	private static ExtentReports createInstance() {
		String fileName = basePath+"/test-output/APIExecutionReport.html";
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.loadXMLConfig(extentConfigFilePath);
		extent = new ExtentReports();
		extent.setSystemInfo("Base URL: ", "https://petstore.swagger.io");
		extent.setSystemInfo("Version: ", "v2");
		extent.setSystemInfo("OS: ", System.getProperty("os.name"));
		extent.attachReporter(htmlReporter); 
		return extent;
	}

	static ExtentReports getInstance() {
		if (extent == null) {
			createInstance();
		}
		return extent;
	}

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		getInstance().flush();
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = getInstance().createTest(testName);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}


}
