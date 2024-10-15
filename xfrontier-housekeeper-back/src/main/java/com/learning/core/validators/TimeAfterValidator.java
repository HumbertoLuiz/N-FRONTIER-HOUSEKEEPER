package com.learning.core.validators;

import java.time.LocalDateTime;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimeAfterValidator implements ConstraintValidator<TimeAfter, LocalDateTime> {

    private int startTime;

    @Override
    public void initialize(TimeAfter constraintAnnotation) {
    	startTime = constraintAnnotation.startTime();
    	validateParameters();
    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        return value.getHour() >= startTime;
    }

    private void validateParameters() {
        if (startTime < 0) {
            throw new IllegalArgumentException("StartTime cannot be negative");
        }
    }

}
