package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredencials;
import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginApiTest {
	private UserCredencials usercredencials;

	@BeforeMethod(description = "Create the Payload for Login API")
	public void setup() {
		usercredencials = new UserCredencials("iamfd", "password");
	}

	@Test(description = "Varify Login Funtionality is working for FD user", groups = { "smoke", "regression" })
	public void loginApiTest() {
		given()
		.spec(requestSpec(usercredencials))
		.when()
		.post("login")
		.then()
		.statusCode(200)
		.body("message", equalTo("Success"))
		.body("data.token", notNullValue())
		.time(lessThan(2500L))
		.body(matchesJsonSchemaInClasspath("schemaValidator/loginApiSchema.json"));

	}

}
