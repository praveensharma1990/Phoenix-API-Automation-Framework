package com.api.filters;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter {

	private static final Logger LOGGER = LogManager.getLogger(SensitiveDataFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		LOGGER.info("+++++++++++++++++RequestBody Details++++++++++++++++++++");
		LOGGER.info("Base URI: {}", requestSpec.getURI());
		LOGGER.info("Request Method: {}", requestSpec.getMethod());
		redactHeader(requestSpec);
		redactPayload(requestSpec);
		Response response = ctx.next(requestSpec, responseSpec);
		LOGGER.info("+++++++++++++++++Response Details++++++++++++++++++++");
		LOGGER.info("STATUS CODE: {}", response.getStatusLine());
		LOGGER.info("RESPONSE TIME: {}", response.timeIn(TimeUnit.MILLISECONDS));
		LOGGER.info("Response Header:\n {}", response.getHeaders());
		RedectResponseBody(response);
		return response;
	}

	private void redactHeader(FilterableRequestSpecification requestSpec) {
	List<Header>headerList=	requestSpec.getHeaders().asList();
	for(Header header:headerList) {
		if(header.getName().equalsIgnoreCase("Authorization")) {
			LOGGER.info("Header {} : {}",header.getName(),"********");
		} else {
			LOGGER.info("Header {} : {}",header.getName(),header.getValue());
		}
	}
		
	}

	private void RedectResponseBody(Response response) {
		String responseBody = response.asPrettyString();
		responseBody = responseBody.replaceAll("\"token\"\\s*:\\s*\"[^\"]+\"", "\"token\":\"****\"");
		LOGGER.info("Response body:\n {}", responseBody);
	}

	private void redactPayload(FilterableRequestSpecification requestSpec) {
		if (requestSpec.getBody() != null) {
			String requestPayload = requestSpec.getBody().toString();
			requestPayload = requestPayload.replaceAll("\"password\"\\s*:\\s*\"[^\"]+\"", "\"password\":\"****\"");
			LOGGER.info("Request payload:\n {}", requestPayload);
		}
	}

}
