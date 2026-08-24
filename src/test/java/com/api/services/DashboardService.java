package com.api.services;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.UserRole;
import com.api.utils.SpecUtil;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class DashboardService {
	
	private static final String COUNT_ENDPOINT = "/dashboard/count";
	private static final String DETAILS_ENDPOINT = "/dashboard/details";
	private static final Logger LOGGER = LogManager.getLogger(DashboardService.class);
	@Step("Making count API request for the Role")
	public Response count(UserRole role) {
    LOGGER.info("Making request to the {} for the role {}",COUNT_ENDPOINT,role);		
	return	given()
		 .spec(requestSpecWithAuth(role))
		 .when()
		 .get(COUNT_ENDPOINT);
	}
	@Step("Making count API request with Auth token")
	public Response countWithNoAuth() {
		LOGGER.info("Making request to the {}",COUNT_ENDPOINT);
		return	given()
		     .spec(SpecUtil.requestSpec())
			 .when()
			 .get(COUNT_ENDPOINT);
		}
	@Step("Making deatails API request")
	public Response details(UserRole role,Object payload) {
		LOGGER.info("Making Details API Request with role {} and the payload{}",DETAILS_ENDPOINT,role);	
		return given()
				.spec(SpecUtil.requestSpecWithAuth(role,payload))
				.when()
				.post(DETAILS_ENDPOINT);
				
	}

}
