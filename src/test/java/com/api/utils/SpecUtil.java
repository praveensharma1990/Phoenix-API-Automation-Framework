package com.api.utils;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.api.utils.ConfigManager.*;

import org.hamcrest.Matchers;

import com.api.constant.UserRole;
import com.api.filters.SensitiveDataFilter;
import com.api.request.model.UserCredencials;

public class SpecUtil {
	
	@Step("Setting up the BaseURI,ContentType and attaching Sensitive Data Filter")
	public static RequestSpecification requestSpec() {
		return new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON)
				.addFilter(new SensitiveDataFilter())
				.build();
		

	}
	@Step("Setting up the BaseURI,ContentType and attaching Sensitive Data Filter with Payload")
	public static RequestSpecification requestSpec(Object payload) {
		return new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON)
				.setBody(payload)
				.addFilter(new SensitiveDataFilter())
				.build();
		
	}
	@Step("Setting up the BaseURI,ContentType and attaching Sensitive Data Filter for role")
	public static RequestSpecification requestSpecWithAuth(UserRole role) {
		return new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.addHeader("Authorization",AuthTokenProvider.getToken(role))
				.setAccept(ContentType.JSON).setContentType(ContentType.JSON)
				.addFilter(new SensitiveDataFilter())
				.build();
	}	
	@Step("Expecting the response to have ContentType JSON,ResponseTime lessThan 1000ms and status 200")
	public static ResponseSpecification responseSpecification() {
	return new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectResponseTime(Matchers.lessThan(1000L))
		.expectStatusCode(200)
		.build();		
	}
	@Step("Expecting the response to have ContentType JSON,ResponseTime lessThan 1000ms and status code")
	public static ResponseSpecification responseSpecification(int status) {
		return new ResponseSpecBuilder().expectContentType(ContentType.JSON)
		.expectResponseTime(Matchers.lessThan(2000L))
		.expectStatusCode(status)
		.build();
	}
	
	@Step("Expecting the contentType as text response to have ResponseTime lessThan 2000ms and status code")
	public static ResponseSpecification responseSpecificationText(int status) {
		return new ResponseSpecBuilder()
		.expectResponseTime(Matchers.lessThan(2000L))
		.expectStatusCode(status)
		.build();
	}
	@Step("Setting up the BaseURI,ContentType and attaching Sensitive Data Filter with role and payload")
	public static RequestSpecification requestSpecWithAuth(UserRole role, Object payload) {
		return new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.addHeader("Authorization",AuthTokenProvider.getToken(role))
				.setBody(payload)
				.setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON)
				.addFilter(new SensitiveDataFilter())
				.build();
	}	
}
