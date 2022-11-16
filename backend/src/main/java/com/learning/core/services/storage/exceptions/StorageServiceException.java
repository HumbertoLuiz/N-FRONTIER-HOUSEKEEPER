package com.learning.core.services.storage.exceptions;

public class StorageServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StorageServiceException() {}

    public StorageServiceException(String message) {
        super(message);
    }

}