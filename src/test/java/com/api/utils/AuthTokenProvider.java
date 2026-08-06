package com.api.utils;

import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.UserRole;
import com.api.request.model.UserCredencials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	private static Map<UserRole, String> tokenCace = new ConcurrentHashMap<UserRole, String>();
	private static String token;
	private static final Logger LOGGER = LogManager.getLogger(AuthTokenProvider.class);

	private AuthTokenProvider() {
		// private constructor to prevent instantiation
	}
	
	
	public static String getToken(UserRole role) {
		LOGGER.info("checking if token is available in the cache for the role {}",role);
		if(tokenCace.containsKey(role)) {
			LOGGER.info("token found for the role {}",role);
			       return tokenCace.get(role);		       
			
		}	
		LOGGER.info("Generating Token for the role {}",role);
		UserCredencials userCredentials = switch (role) {
		case FD -> new UserCredencials("iamfd", "password");
		case SUP -> new UserCredencials("iamsup", "password");
		case ENG -> new UserCredencials("iameng", "password");
		case QC -> new UserCredencials("iamqc", "password");
		default -> throw new IllegalArgumentException("Invalid role: " + role);
		};

	token=given().baseUri(ConfigManager.getProperty("BASE_URI")).accept(ContentType.JSON)
				.contentType(ContentType.JSON).body(userCredentials).when().post("/login").then().log()
				.ifError().extract().body().jsonPath().getString("data.token");
		   tokenCace.put(role, token);
		   return token;	

	
}
}
