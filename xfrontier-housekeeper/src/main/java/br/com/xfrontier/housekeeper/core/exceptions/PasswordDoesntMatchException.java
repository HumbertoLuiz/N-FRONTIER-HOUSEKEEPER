package br.com.xfrontier.housekeeper.core.exceptions;

import org.springframework.validation.FieldError;

public class PasswordDoesntMatchException extends ValidatingException {

	private static final long serialVersionUID = 1L;

	public PasswordDoesntMatchException(String message, FieldError fieldError) {
        super(message, fieldError);
    }
}
