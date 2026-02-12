package com.api.utils;

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
import com.api.pojo.UserCredencials;

public class SpecUtil {

	public static RequestSpecification requestSpec() {
		return new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON)
				.log(LogDetail.URI)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.log(LogDetail.METHOD)
				.build();
		

	}

	public static RequestSpecification requestSpec(Object payload) {
		return new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON)
				.setBody(payload)
				.log(LogDetail.URI)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.log(LogDetail.METHOD)
				.build();
		
	}
	public static RequestSpecification requestSpecWithAuth(UserRole role) {
		return new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.addHeader("Authorization",AuthTokenProvider.getToken(role))
				.setAccept(ContentType.JSON).setContentType(ContentType.JSON)
				.log(LogDetail.URI)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.log(LogDetail.METHOD)
				.build();
	}
	
	
	public static ResponseSpecification responseSpecification() {
	return new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectResponseTime(Matchers.lessThan(1000L))
		.expectStatusCode(200)
		.log(LogDetail.ALL)
		.build();		
	}
	
	public static ResponseSpecification responseSpecification(int status) {
		return new ResponseSpecBuilder().expectContentType(ContentType.JSON)
		.expectResponseTime(Matchers.lessThan(2000L))
		.expectStatusCode(status)
		.log(LogDetail.ALL)
		.build();
	}
	
	public static ResponseSpecification responseSpecificationText(int status) {
		return new ResponseSpecBuilder()
		.expectResponseTime(Matchers.lessThan(2000L))
		.expectStatusCode(status)
		.log(LogDetail.ALL)
		.build();
	}
}
