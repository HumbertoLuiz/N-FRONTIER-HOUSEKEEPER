package com.learning.core.validators;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import com.learning.core.enums.DailyStatus;
import com.learning.core.exceptions.ValidatingException;
import com.learning.core.models.Daily;

@Component
public class TransferValidator {

    public void validate(Daily daily) {
        validateStatus(daily);
    }

    private void validateStatus(Daily daily) {
        var statusValidos = List.of(DailyStatus.CONCLUDED, DailyStatus.EVALUATED);
        if (!statusValidos.contains(daily.getStatus())) {
            var message = "Invalid status for transfer";
            var fieldError = new FieldError(daily.getClass().getName(), "status", daily.getStatus(), false, null, null, message);
            throw new ValidatingException(message, fieldError);
        }
    }

}
