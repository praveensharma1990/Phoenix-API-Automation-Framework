package com.api.services;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.UserRole;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class UserService {
	private static final String USERDETAILS_ENDPOINT ="/userdetails";
	private static final Logger LOGGER = LogManager.getLogger(UserService.class);
	
	@Step("Making UserDetails API request")
	public Response userDetails(UserRole role) {
		LOGGER.info("Making request for {} with the role {}",USERDETAILS_ENDPOINT,role);
		return given()
		.spec(requestSpecWithAuth(role))
		.when()
		.get(USERDETAILS_ENDPOINT);
		
	}

}
