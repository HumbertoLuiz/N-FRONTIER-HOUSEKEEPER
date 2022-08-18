package com.learning.core.exceptions;

import org.springframework.validation.FieldError;

public class ValidationException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
	
	private FieldError fieldError;

    public ValidationException(String message, FieldError fieldError) {
        super(message);
        this.fieldError = fieldError;
    }

    public FieldError getFieldError() {
        return fieldError;
    }
}
