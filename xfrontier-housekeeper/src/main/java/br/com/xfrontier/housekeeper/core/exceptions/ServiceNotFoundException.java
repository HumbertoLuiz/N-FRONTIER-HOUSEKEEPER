package br.com.xfrontier.housekeeper.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ServiceNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public ServiceNotFoundException(String msg) {
		super(msg);
	}
}
