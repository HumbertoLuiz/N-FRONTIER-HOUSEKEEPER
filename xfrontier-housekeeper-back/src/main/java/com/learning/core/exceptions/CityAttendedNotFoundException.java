package com.learning.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class CityAttendedNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

	public CityAttendedNotFoundException(String message) {
        super(message);
    }

}
