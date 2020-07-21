package com.api.utils;

import org.testng.Reporter;

public class Logs {
	
	public static void info(String message) {
		Reporter.log(message, true);
	}

}
