package com.api.test;

import static com.api.utils.SpecUtil.responseSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.UserRole;
import com.api.services.UserService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Listeners(com.listeners.APITestListener.class)
@Epic("User Management")
@Feature("User Details")
public class UserDetailsApiTest {
	
	private UserService userDetailsService;
	
	@BeforeMethod(description = "Inisialize user details service")
	public void setup() {
		userDetailsService = new UserService();
	}
	@Story("User Details should be shown")
	@Description("valiate User Details api response is shwon correctly")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description="valiate User Details api response is shwon correctly",groups= {"smoke","regression"})
	public void getUserDetailsTest() {
		
		userDetailsService.userDetails(UserRole.FD)
		.then()
		.body(matchesJsonSchemaInClasspath("schemaValidator/getUserDetailsSchema.json"))
		.spec(responseSpecification());		
	}
}
