package com.learning.core.exceptions;

import org.springframework.validation.FieldError;

public class SenhaIncorretaException extends ValidatingException {

    private static final long serialVersionUID = 1L;

	public SenhaIncorretaException(String message, FieldError fieldError) {
        super(message, fieldError);
    }
    
}
