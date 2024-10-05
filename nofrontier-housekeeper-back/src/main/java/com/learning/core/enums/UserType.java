package com.learning.core.enums;

public enum UserType {
	
    CUSTOMER (1),
    HOUSEKEEPER (2),
    ADMIN (3);

    private Integer id;

	private UserType() {}
	
	private UserType(Integer id) {
		this.id = id;
	}
    
	public Integer getId() {
		return id;
	}
}
