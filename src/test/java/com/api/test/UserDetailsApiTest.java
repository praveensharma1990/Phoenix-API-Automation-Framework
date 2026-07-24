package com.api.test;

import static com.api.utils.SpecUtil.responseSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.UserRole;
import com.api.services.UserService;

public class UserDetailsApiTest {
	
	private UserService userDetailsService;
	
	@BeforeMethod(description = "Inisialize user details service")
	public void setup() {
		userDetailsService = new UserService();
	}
	@Test(description="valiate User Details api response is shwon correctly",groups= {"smoke","regression"})
	public void getUserDetailsTest() {
		
		userDetailsService.userDetails(UserRole.FD)
		.then()
		.body(matchesJsonSchemaInClasspath("schemaValidator/getUserDetailsSchema.json"))
		.spec(responseSpecification());		
	}
}
