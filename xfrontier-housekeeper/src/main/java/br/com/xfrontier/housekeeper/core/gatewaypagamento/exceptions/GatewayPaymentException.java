package br.com.xfrontier.housekeeper.core.gatewaypagamento.exceptions;

public class GatewayPaymentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public GatewayPaymentException() {}

    public GatewayPaymentException(String message) {
        super(message);
    }

}
