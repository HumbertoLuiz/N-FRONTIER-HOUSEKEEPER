package com.learning.core.enums;

public enum Icon {

	HL_CLEANING_1("hl_cleaning_1"),
	HL_CLEANING_2("hl_cleaning_2"),
	HL_CLEANING_3("hl_cleaning_3");
	
	private String name;
	
	private Icon(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
