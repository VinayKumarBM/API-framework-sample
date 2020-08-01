package com.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;

public class Logs {
	private static final Logger LOG = LogManager.getLogger(Logs.class.getName());
	
	public static void info(String message) {
		Reporter.log(message, true);
		LOG.info(message);
	}

}
