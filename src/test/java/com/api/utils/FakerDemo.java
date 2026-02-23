package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;

public class FakerDemo {
	
	
	
	public static void main(String args[]) {
		Faker faker = new Faker(new Locale("en-IND"));
		for(int i=0;i<10;i++) {
		System.out.println(faker.name().firstName());
		System.out.println(faker.name().lastName());
		System.out.println(faker.numerify("9990036###"));
		}
		
	
		
		
		
	}

}
