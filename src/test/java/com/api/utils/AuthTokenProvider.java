package com.api.utils;

import static io.restassured.RestAssured.given;

import com.api.constant.UserRole;
import com.api.request.model.UserCredencials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {

	private AuthTokenProvider() {
		// private constructor to prevent instantiation
	}

	public static String getToken(UserRole role) {
		UserCredencials userCredentials = switch (role) {
		case FD -> new UserCredencials("iamfd", "password");
		case SUP -> new UserCredencials("iamsup", "password");
		case ENG -> new UserCredencials("iameng", "password");
		case QC -> new UserCredencials("iamqc", "password");
		default -> throw new IllegalArgumentException("Invalid role: " + role);
		};

		return given().baseUri(ConfigManager.getProperty("BASE_URI")).accept(ContentType.JSON)
				.contentType(ContentType.JSON).body(userCredentials).log().all().when().post("/login").then().log()
				.all().extract().body().jsonPath().getString("data.token");
		

	}
}
