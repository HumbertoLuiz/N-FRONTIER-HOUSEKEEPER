package br.com.xfrontier.housekeeper.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class PaymentNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

	public PaymentNotFoundException() {
        super("Payment not found");
    }

    public PaymentNotFoundException(String message) {
        super(message);
    }

}
