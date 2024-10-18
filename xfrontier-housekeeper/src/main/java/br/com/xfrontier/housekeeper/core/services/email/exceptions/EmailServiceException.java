package br.com.xfrontier.housekeeper.core.services.email.exceptions;

public class EmailServiceException extends RuntimeException {
    private static final long serialVersionUID= 1L;

    public EmailServiceException() {}

    public EmailServiceException(String message) {
        super(message);
    }
}
