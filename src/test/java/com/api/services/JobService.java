package com.api.services;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constant.UserRole;
import com.api.request.model.CreateJobPayload;

import io.restassured.response.Response;

public class JobService {
	private static final String CREATE_JOB_ENDPOINT = "/job/create";
	private static final String SEARCH_JOB_ENDPOINT = "/job/search";
	
	public Response createJob(UserRole role,CreateJobPayload payload) {
		
	return	given()
		.spec(requestSpecWithAuth(role,payload))
		.when().post(CREATE_JOB_ENDPOINT);
	}
	
	public Response search(UserRole role, Object payload) {
		return given()
				.spec(requestSpecWithAuth(role,payload))
				.when().post(SEARCH_JOB_ENDPOINT);
	}
	

}
