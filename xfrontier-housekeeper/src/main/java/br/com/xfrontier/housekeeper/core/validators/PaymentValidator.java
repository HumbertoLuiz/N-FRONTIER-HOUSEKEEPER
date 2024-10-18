package br.com.xfrontier.housekeeper.core.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import br.com.xfrontier.housekeeper.core.exceptions.ValidatingException;
import br.com.xfrontier.housekeeper.core.models.Daily;

@Component
public class PaymentValidator {

    public void validate(Daily daily) {
        validateStatus(daily);
    }

    private void validateStatus(Daily daily) {
        if (!daily.isNoPayment()) {
            var message = "the daily rate must have the status WITHOUT PAYMENT";
            var fieldError = new FieldError(daily.getClass().getName(), "status", daily.getStatus(), false, null, null, message);

            throw new ValidatingException(message, fieldError);
        }
    }

}
