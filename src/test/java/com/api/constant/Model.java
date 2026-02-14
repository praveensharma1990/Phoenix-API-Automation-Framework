package com.api.constant;

public enum Model {
	NEXUS_2_BLUE(1),GALAXY(2);
	
	int modelCode;
	private Model(int modelCode){
		this.modelCode=modelCode;
	}
	
	public int getModelCode() {
		return modelCode;
	}

}
