package com.api.pojo;

public class CustomerAddress {
	private String flat_number;
	private String apartment_name;
	private String street_name;
	private String area;
	private String landmark;
	private String pincode;
	private String state;
	private String country;

	@Override
	public String toString() {
		return "CustomerAddress [flat_number=" + flat_number + ", apartment_name=" + apartment_name + ", street_name="
				+ street_name + ", area=" + area + ", landmark=" + landmark + ", pincode=" + pincode + ", state="
				+ state + ", country=" + country + "]";
	}

	public String getFlat_number() {
		return flat_number;
	}

	public void setFlat_number(String flat_number) {
		this.flat_number = flat_number;
	}

	public String getApartment_name() {
		return apartment_name;
	}

	public void setApartment_name(String apartment_name) {
		this.apartment_name = apartment_name;
	}

	public String getStreet_name() {
		return street_name;
	}

	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public CustomerAddress(String flat_number, String apartment_name, String street_name, String area, String landmark,
			String pincode, String state, String country) {
		super();
		this.flat_number = flat_number;
		this.apartment_name = apartment_name;
		this.street_name = street_name;
		this.area = area;
		this.landmark = landmark;
		this.pincode = pincode;
		this.state = state;
		this.country = country;
	}
}
