package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.UserRole;
import com.api.request.model.Detail;
import com.api.services.DashboardService;

public class DetailsApiTest {
	
	private DashboardService dashboardService;
	private Detail detailPayload;
	@BeforeMethod(description = "Instantiating the Dashboard Service")
	public void setup() {
		dashboardService = new DashboardService();
		detailPayload = new Detail("created_today");
	}	
	
	@Test(description = "validate the details Api test",groups = {"Smoke","api"})
	public void DetailApiTest() {
		dashboardService.details(UserRole.FD, detailPayload)
		.then().log().ifValidationFails()
		.statusCode(200)
		.body("message",Matchers.equalToIgnoringCase("success"));
	}

}
