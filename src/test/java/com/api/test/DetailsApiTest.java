package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.UserRole;
import com.api.request.model.Detail;
import com.api.services.DashboardService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Listeners(com.listeners.APITestListener.class)
@Epic("Job Management")
@Feature("Job Details")
public class DetailsApiTest {
	
	private DashboardService dashboardService;
	private Detail detailPayload;
	@BeforeMethod(description = "Instantiating the Dashboard Service")
	public void setup() {
		dashboardService = new DashboardService();
		detailPayload = new Detail("created_today");
	}	
	
	@Test(description = "validate the details Api test",groups = {"Smoke","api"})
	@Story("Job Details is showin correctly for FD")
	@Description("validate the details Api test is working find for user FD")
	@Severity(SeverityLevel.CRITICAL)
	public void DetailApiTest() {
		dashboardService.details(UserRole.FD, detailPayload)
		.then().log().ifValidationFails()
		.statusCode(200)
		.body("message",Matchers.equalToIgnoringCase("success"));
	}

}
