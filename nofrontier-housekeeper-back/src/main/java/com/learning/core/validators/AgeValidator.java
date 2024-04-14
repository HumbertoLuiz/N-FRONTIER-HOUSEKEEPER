package com.learning.core.validators;

import java.time.LocalDate;
import java.time.Period;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<Age, LocalDate>{

    private int min;
    private int max;

    @Override
    public void initialize(Age constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
        validateParameters();
    }

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		if(value == null) {
			return true;
		}
		var currentDate = LocalDate.now();
		var age = Period.between(value, currentDate).getYears();
		return age >= min && age <= max;
	}

	private void validateParameters() {
		if (min < 0) {
			throw new IllegalArgumentException("The parameter min cannot be negative");
		}
		if (max < 0) {
			throw new IllegalArgumentException("The parameter max cannot be negative");
		}
		if (max < min) {
			throw new IllegalArgumentException("The parameter max cannot be less than min");
		}
	}
}
