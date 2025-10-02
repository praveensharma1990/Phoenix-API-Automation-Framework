package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;
import com.api.constant.UserRole;
import com.api.utils.AuthTokenProvider;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsApiTest {
	
	@Test()
	public void getUserDetailsTest() {
		Header authHeader=new Header("authorization", AuthTokenProvider.getToken(UserRole.ENG));
		given()
		.baseUri(getProperty("BASE_URI"))
		.accept(ContentType.JSON)
		.header(authHeader)
		.log().all()
		.when()
		.get("userdetails")
		.then()
		.statusCode(200)
		.body("message",equalTo("Success"))
		.time(lessThan(1500L))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemaValidator/getUserDetailsSchema.json"))
		.log().all();
		

		
	}

}
