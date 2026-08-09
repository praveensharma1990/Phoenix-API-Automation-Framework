package com.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class APITestListener implements ITestListener {

	public static final Logger LOGGER = LogManager.getLogger(APITestListener.class);

	@Override
	public void onTestStart(ITestResult result) {
		LOGGER.info("****************************************************************");
		LOGGER.info("============== Starting the test {}================", result.getName());
		LOGGER.info("==============Test Class {}==============", result.getMethod().getTestClass());
		LOGGER.info("==============Description {}==============", result.getMethod().getDescription());
		LOGGER.info("==============Groups {}==============", Arrays.toString(result.getMethod().getGroups()));
		LOGGER.info("****************************************************************");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Long startTime = result.getStartMillis();
		Long endTime = result.getEndMillis();
		LOGGER.info("Total Duration {}ms", endTime - startTime);
		LOGGER.info("{} test passed", result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		LOGGER.error("{} test failed", result.getName());
		LOGGER.error("error", result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		LOGGER.info("{} test skipped", result.getName());
		LOGGER.error("error ", result.getThrowable());

	}

	public void onStart(ITestContext context) {
		LOGGER.info("*******************starting Phoenix Test automation*****************************");

	}

	public void onFinish(ITestContext context) {

		LOGGER.info("****************************Finish*********************************************");

	}

}
