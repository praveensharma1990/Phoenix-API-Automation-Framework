package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojo.UserCredencials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest {
	UserCredencials usercredencials = new UserCredencials("iamfd", "password");
     
	@Test()
	 void loginApiTest() {
		given().baseUri("http://64.227.160.186:9000/v1").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(usercredencials).log().headers().log().method().log().uri().log().body().log().headers().when().post("login")
				.then().statusCode(200).body("message", equalTo("Success")).body("data.token", notNullValue())
				.time(lessThan(1000L))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemaValidator/loginApiSchema.json"));

	}

}
