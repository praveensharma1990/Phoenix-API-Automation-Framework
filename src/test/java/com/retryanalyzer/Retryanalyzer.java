package com.retryanalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retryanalyzer implements IRetryAnalyzer{
	private static final Logger LOGGER = LogManager.getLogger(Retryanalyzer.class);
	private static final int MAX_ATTEMPT = 2;
	private int count = 1;

	@Override
	public boolean retry(ITestResult result) {
		LOGGER.info("Checking if the {} can be Re-executed",result.getName());
		while(count <= MAX_ATTEMPT) {
			LOGGER.warn("Executing the {}, current attempt:{}/{},reason{}",result.getName(),count,MAX_ATTEMPT,result.getThrowable().getMessage());
			count++;
			return true;
		}
		return false;
	}

}
