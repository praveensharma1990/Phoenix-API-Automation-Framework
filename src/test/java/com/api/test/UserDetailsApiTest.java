package com.api.test;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constant.UserRole;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsApiTest {
	
	@Test()
	public void getUserDetailsTest() {
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(UserRole.FD))
		.when()
		.get("userdetails")
		.then()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemaValidator/getUserDetailsSchema.json"))
		.spec(SpecUtil.responseSpecification());
		

		
	}

}
