package com.api.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTimeProvider {
	
	private DateTimeProvider() {
		
	}
	
	public static String getDateAndTimeDaysAgo(int days) {
		
		 return Instant.now().minus(days,ChronoUnit.DAYS).toString();
	}

}

