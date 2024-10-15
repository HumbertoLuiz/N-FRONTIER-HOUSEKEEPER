package com.learning.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class DailyNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

	public DailyNotFoundException(String message) {
        super(message);
    }

}
