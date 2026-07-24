package com.api.services;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constant.UserRole;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class MasterService {

	private static final String MASTER_ENDPOINT = "/master";
	
	public Response master(UserRole role) {
	return given()
		.spec(requestSpecWithAuth(role))
		.when()
		.post(MASTER_ENDPOINT);

	}
	
	public Response masterWithoutAuth() {
		return given()
			.spec(SpecUtil.requestSpec())
			.when()
			.post(MASTER_ENDPOINT);

		}
}
