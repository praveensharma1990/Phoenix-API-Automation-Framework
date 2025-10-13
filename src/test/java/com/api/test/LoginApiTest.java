package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.api.pojo.UserCredencials;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest {
	UserCredencials usercredencials = new UserCredencials("iamfd", "password");
     
    
	@Test()
	 void loginApiTest() {
		given().spec(SpecUtil.requestSpec(usercredencials))
				.when().post("login")
				.then().statusCode(200).body("message", equalTo("Success")).body("data.token", notNullValue())
				.time(lessThan(2500L))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemaValidator/loginApiSchema.json"));

	}

}
