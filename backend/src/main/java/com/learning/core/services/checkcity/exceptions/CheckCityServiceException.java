package com.learning.core.services.checkcity.exceptions;

public class CheckCityServiceException extends RuntimeException {

    private static final long serialVersionUID= 1L;

    public CheckCityServiceException() {}

    public CheckCityServiceException(String message) {
        super(message);
    }

}