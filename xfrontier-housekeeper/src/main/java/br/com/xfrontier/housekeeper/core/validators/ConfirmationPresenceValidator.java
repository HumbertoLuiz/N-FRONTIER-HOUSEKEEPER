package br.com.xfrontier.housekeeper.core.validators;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import br.com.xfrontier.housekeeper.core.exceptions.ValidatingException;
import br.com.xfrontier.housekeeper.core.models.Daily;

@Component
public class ConfirmationPresenceValidator {

    public void validate(Daily daily) {
        validateStatus(daily);
    }

    private void validateStatus(Daily daily) {
        if (!daily.isConfirmed()) {
            var message = "Daily rate not in CONFIRMED status";
            var fieldError = new FieldError(daily.getClass().getName(), "status", daily.getStatus(), false, null, null, message);
            throw new ValidatingException(message, fieldError);
        }

        validteDateService(daily);
    }

    private void validteDateService(Daily daily) {
        var today = LocalDateTime.now();
        var dateService = daily.getDateService();

        if (dateService.isAfter(today)) {
            var message = "The per diem service date must be in the past";
            var fieldError = new FieldError(daily.getClass().getName(), "dateService", daily.getDateService(), false, null, null, message);
            throw new ValidatingException(message, fieldError);
        }

        validateHousekeeper(daily);
    }

    private void validateHousekeeper(Daily daily) {
        if (daily.getHousekeeper() == null) {
            var message = "The daily rate does not include a selected diarist";
            var fieldError = new FieldError(daily.getClass().getName(), "housekeeper", daily.getHousekeeper(), false, null, null, message);
            throw new ValidatingException(message, fieldError);
        }
    }

}
