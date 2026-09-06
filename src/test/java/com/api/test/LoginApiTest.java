package com.api.test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.dataproviders.api.bean.UserBean;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Listeners(com.listeners.APITestListener.class)
@Epic("User Management")
@Feature("Authentication")
public class LoginApiTest {
	private UserBean usercredencials;
	private AuthService authService;

	@BeforeMethod(description = "Create the Payload for Login API")
	public void setup() {
		usercredencials = new UserBean("iamfd", "password");
		authService = new AuthService();
	}
    @Story("Valid User should be able to login the system")
    @Description("Varify Login Functionality is working")
    @Severity(SeverityLevel.BLOCKER)
	@Test(description = "Varify Login Funtionality is working for FD user", groups = { "smoke", "regression" },
	retryAnalyzer = com.retryanalyzer.Retryanalyzer.class)
	public void loginApiTest() {
		authService.login(usercredencials)
		.then()
		.statusCode(200)
		.body("message", equalTo("Success"))
		.body("data.token", notNullValue())
		.time(lessThan(2500L))
		.body(matchesJsonSchemaInClasspath("schemaValidator/loginApiSchema.json"));

	}

}
