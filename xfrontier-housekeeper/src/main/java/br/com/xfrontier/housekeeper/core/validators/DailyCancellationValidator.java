package br.com.xfrontier.housekeeper.core.validators;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import br.com.xfrontier.housekeeper.core.exceptions.ValidatingException;
import br.com.xfrontier.housekeeper.core.models.Daily;

@Component
public class DailyCancellationValidator {

    public void validar(Daily daily) {
        validateStatus(daily);
    }

    private void validateStatus(Daily daily) {
        var isNotPaidOrNotConfirmed = !(daily.isPaid() || daily.isConfirmed());
        if (isNotPaidOrNotConfirmed) {
            var message = "Daily rate to be canceled must have the status PAID or CONFIRMED";
            var fieldError = new FieldError(daily.getClass().getName(), "status", daily.getStatus(), false, null, null, message);
            throw new ValidatingException(message, fieldError);
        }

        validateDateService(daily);
    }

    private void validateDateService(Daily daily) {
        var today = LocalDateTime.now();
        var isDateServiceInPast = daily.getDateService().isBefore(today);
        if (isDateServiceInPast) {
            var message = "It is no longer possible to cancel the daily rate.";
            var fieldError = new FieldError(daily.getClass().getName(), "dateService", daily.getDateService(), false, null, null, message);
            throw new ValidatingException(message, fieldError);
        }
    }

}
