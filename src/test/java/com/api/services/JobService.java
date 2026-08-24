package com.api.services;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.UserRole;
import com.api.request.model.CreateJobPayload;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class JobService {
	private static final String CREATE_JOB_ENDPOINT = "/job/create";
	private static final String SEARCH_JOB_ENDPOINT = "/job/search";
	private static final Logger LOGGER =LogManager.getLogger(JobService.class);
	@Step("Create inwarranty job with create job api")
	public Response createJob(UserRole role,CreateJobPayload payload) {
		LOGGER.info("Making request for {} with role {} and payload{}",CREATE_JOB_ENDPOINT,role,payload);
	return	given()
		.spec(requestSpecWithAuth(role,payload))
		.when().post(CREATE_JOB_ENDPOINT);
	}
	@Step("Making search API request")
	public Response search(UserRole role, Object payload) {
		LOGGER.info("Making request for {} with role {} and payload{}",SEARCH_JOB_ENDPOINT,role,payload);
		return given()
				.spec(requestSpecWithAuth(role,payload))
				.when().post(SEARCH_JOB_ENDPOINT);
	}
	

}
