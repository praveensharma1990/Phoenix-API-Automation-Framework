package com.api.services;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import javax.management.relation.Role;

import com.api.constant.UserRole;
import com.api.utils.SpecUtil;

import groovyjarjarantlr4.v4.parse.ANTLRParser.finallyClause_return;
import io.restassured.response.Response;

public class DashboardService {
	
	private static final String COUNT_ENDPOINT = "/dashboard/count";
	private static final String DETAILS_ENDPOINT = "/dashboard/details";
	
	public Response count(UserRole role) {
	return	given()
		 .spec(requestSpecWithAuth(role))
		 .when()
		 .get(COUNT_ENDPOINT);
	}
	
	public Response countWithNoAuth() {
		return	given()
		     .spec(SpecUtil.requestSpec())
			 .when()
			 .get(COUNT_ENDPOINT);
		}
	
	public Response details(UserRole role,Object payload) {
		return given()
				.spec(SpecUtil.requestSpecWithAuth(role,payload))
				.when()
				.post(DETAILS_ENDPOINT);
				
	}

}
