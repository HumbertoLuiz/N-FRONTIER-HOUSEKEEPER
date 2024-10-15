package com.learning.core.validators;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import com.learning.core.exceptions.ValidatingException;
import com.learning.core.models.Rating;
import com.learning.core.repository.RatingRepository;

@Component
public class RatingValidator {

    @Autowired
    private RatingRepository repository;

    public void validate(Rating model) {
        validateDailyStatus(model);
    }

    private void validateDailyStatus(Rating model) {
        if (!model.getDaily().isConcluded()) {
            var message = "Daily rate does not have status COMPLETED";
            var fieldError = new FieldError(model.getClass().getName(), "daily", null, false, null, null, message);
            throw new ValidatingException(message, fieldError);
        }

        validateDailyDateService(model);
    }

    private void validateDailyDateService(Rating model) {
        var today = LocalDateTime.now();
        var dailyDateService = model.getDaily().getDateService();

        if (dailyDateService.isAfter(today)) {
            var message = "Daily rate is with the date of service in the future";
            var fieldError = new FieldError(model.getClass().getName(), "daily", null, false, null, null, message);
            throw new ValidatingException(message, fieldError);
        }

        validateEvaluator(model);
    }

    private void validateEvaluator(Rating model) {
        var daily = model.getDaily();
        var evaluator = model.getEvaluator();

        if (repository.existsByDailyAndEvaluator(daily, evaluator)) {
            var message = "User has already rated this daily";
            var fieldError = new FieldError(model.getClass().getName(), "evaluator", null, false, null, null, message);
            throw new ValidatingException(message, fieldError);
        }
    }

}
