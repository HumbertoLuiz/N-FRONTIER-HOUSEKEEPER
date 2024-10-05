package com.learning.core.enums;

public enum DailyStatus {

	NO_PAYMENT(1, "Waiting for payment"),
	PAID(2, "Paid"),
	CONFIRMED(3, "Selected Diarist"),
	CONCLUDED(4, "Confirmed attendance"),
	CANCELLED(5, "Cancelled"),
	EVALUATED(6, "Evaluated"),
	TRANSFERRED(7, "Transferred to Diarist");

    private Integer id;

    private String description;
    
	private DailyStatus() {}

	private DailyStatus(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
    
}
