package com.api.constant;

public enum WarrentyStatus {
	IN_WARRANTY(1),OUT_WARRANTY(2);
	
	int code;
	
	WarrentyStatus(int code){
		this.code = code;
			}
	
	public int getCode() {
		return code;
	}

}
