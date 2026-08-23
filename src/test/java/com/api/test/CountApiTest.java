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

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.DashboardService;

import static com.api.utils.SpecUtil.*;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
@Listeners(com.listeners.APITestListener.class)
@Epic("Job Management")
@Feature("Job Count")
public class CountApiTest {
	private DashboardService dashboardService;
	
	@BeforeMethod(description = "Instanciate dashboard service")
	public void setup()
	{
		dashboardService = new DashboardService();
	}
	
	@Test(description="validate count api response is correct",groups= {"smoke","regression"})
	@Story("Job count data is shown correctly")
	@Description("validate count api response is correct")
	@Severity(SeverityLevel.CRITICAL)
	public void validateCountApiResponse() {
	 dashboardService.count(FD)		
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
	  dashboardService.countWithNoAuth()	
	 .then()
	 .spec(responseSpecificationText(401));	
		
	}

}
