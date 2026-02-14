package com.api.test;

import static com.api.constant.UserRole.FD;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CountApiTest {
	@Test(description="validate count api response is correct",groups= {"smoke","regression"})
	public void validateCountApiResponse() {
		given()
	 .spec(requestSpecWithAuth(FD))
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
     .body(matchesJsonSchemaInClasspath("schemaValidator/countApiSchema.json"))
	 .log().all();		
		
	}
	@Test(description="Validate Negative test for Invalid Token for Count API",groups= {"negative","smoke","regression"})
	void validateMissingTokenInCountApi() {
	  given()
	 .baseUri(getProperty("BASE_URI"))
	 .accept(ContentType.JSON)
	 .when()
	 .get("dashboard/count")	
	 .then()
	 .spec(responseSpecificationText(401));	
		
	}

}
