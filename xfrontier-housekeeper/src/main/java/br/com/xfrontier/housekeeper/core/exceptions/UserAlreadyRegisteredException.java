package br.com.xfrontier.housekeeper.core.exceptions;

import org.springframework.validation.FieldError;

public class UserAlreadyRegisteredException extends ValidatingException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyRegisteredException(String message, FieldError fieldError) {
		super(message, fieldError);
	}

}
