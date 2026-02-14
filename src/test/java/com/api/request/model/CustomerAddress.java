package com.api.request.model;

public record CustomerAddress (
 String flat_number,
 String apartment_name,
 String street_name,
 String area,
 String landmark,
 String pincode,
 String state,
 String country
)
{
	
}