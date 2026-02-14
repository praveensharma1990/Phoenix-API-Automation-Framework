package com.api.constant;

public enum product {
	
	NEXUS_2(1),PIXEL(2);
	
	int code;
	
	private product(int code) {
		this.code =code;
	}
	
	public int getCode() {
		return code;
	}
	

}
