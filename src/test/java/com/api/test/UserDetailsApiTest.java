package com.api.test;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static com.api.constant.UserRole.*;
import static com.api.utils.SpecUtil.*;

public class UserDetailsApiTest {
	
	@Test(description="valiate User Details api response is shwon correctly",groups= {"smoke","regression"})
	public void getUserDetailsTest() {
		
		given()
		.spec(requestSpecWithAuth(FD))
		.when()
		.get("userdetails")
		.then()
		.body(matchesJsonSchemaInClasspath("schemaValidator/getUserDetailsSchema.json"))
		.spec(responseSpecification());		
	}
}
