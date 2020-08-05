package com.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

public class Logs {
	private static final Logger LOG = LogManager.getLogger(Logs.class.getName());
	
	public static void info(String message) {
		Reporter.log(message, true);
		LOG.info(message);
		ReportManager.getTest().log(Status.PASS,message);
	}

	public static void fail(Throwable throwable) {
		LOG.error(throwable);
		ReportManager.getTest().log(Status.FAIL, throwable);
	}
	
	public static void testDetails(String testName, String message) {
		LOG.info("===============================================================================");
		LOG.info("\t\t\t"+String.format("%S - %S", testName, message)+"");
		LOG.info("===============================================================================");
	}
}
