package com.api.services;

import static com.api.utils.SpecUtil.requestSpec;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.request.model.UserCredencials;
import com.dataproviders.api.bean.UserBean;

import io.restassured.response.Response;

public class AuthService {
	
	private static final String LOGIN_ENDPOINT = "/login";
	private static final Logger LOGGER = LogManager.getLogger(AuthService.class);
	
	public Response login(Object userCredencials) {
		LOGGER.info("making login request for the payload {}",((UserBean)userCredencials).getUsername());
	return	given()
		.spec(requestSpec(userCredencials))
		.when()
		.post(LOGIN_ENDPOINT);
	}
}
