package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import static com.api.constant.UserRole.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountApiTest {
	@Test()
	public void validateCountApiResponse() {
		given()
	 .spec(SpecUtil.requestSpecWithAuth(FD))
	 .when()
	 .get("dashboard/count")	
	 .then()
	 .statusCode(200)
	 .body("message", equalTo("Success"))
	 .time(lessThan(2000L))
	 .body("data", notNullValue())
	 .body("data.size()",equalTo(3))
	 .body("data.count",everyItem(greaterThanOrEqualTo(0)))
	 .body("data.label", everyItem(not(blankOrNullString())))
     .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemaValidator/countApiSchema.json"))
	 .log().all();
	 
		
		
	}
	@Test()
	void validateMissingTokenInCountApi() {
		enableLoggingOfRequestAndResponseIfValidationFails();
		given()
	 .baseUri(getProperty("BASE_URI"))
	 .accept(ContentType.JSON)
	 .when()
	 .get("dashboard/count")	
	 .then()
	 .statusCode(401)
	 .log().all();
	 
	
		
	}

}
