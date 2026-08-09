package com.api.test.datadriven;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredencials;
import com.api.services.AuthService;
import com.dataproviders.api.bean.UserBean;

public class LoginApiJsonDataDrivenTest {
	private AuthService authService;

	@BeforeMethod(description = "Initialize AuthService")
	public void setup() {
		authService = new AuthService();
	}

	@Test(description = "Varify Login Funtionality is working for valid user", groups = { "smoke", "regression",
			"datadriven" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "loginApiJsonDataprovider")
	public void loginApiTest(UserBean usercredencial) {
		authService.login(usercredencial)
		.then()
		.statusCode(200)
		.body("message", equalTo("Success"))
	    .body("data.token", notNullValue()).time(lessThan(2500L))
		.body(matchesJsonSchemaInClasspath("schemaValidator/loginApiSchema.json"));

	}

}
