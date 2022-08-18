package com.learning.core.exceptions;

import javax.persistence.EntityNotFoundException;

public class ServiceNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public ServiceNotFoundException(String msg) {
		super(msg);
	}
}
