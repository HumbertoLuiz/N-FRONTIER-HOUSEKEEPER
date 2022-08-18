package com.learning.core.enums;

public enum Icon {

	TWF_CLEANING_1("twf_cleaning_1"),
	TWF_CLEANING_2("twf_cleaning_2"),
	TWF_CLEANING_3("twf_cleaning_3");
	
	private String name;
	
	private Icon(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
