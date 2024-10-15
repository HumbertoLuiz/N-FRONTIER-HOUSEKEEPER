package com.learning.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class HousekeeperAddressNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

	public HousekeeperAddressNotFoundException(String message) {
        super(message);
    }

}
