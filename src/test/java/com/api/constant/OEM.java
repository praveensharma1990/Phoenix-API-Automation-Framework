package com.api.constant;

public enum OEM {
 Google(1),APPLE(2);
	
	int code;
	
	private OEM(int code) {
		this.code=code;
	}
	
	public int getCode() {
		return code;
	}
}
