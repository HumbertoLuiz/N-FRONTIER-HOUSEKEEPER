package com.learning.core.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learning.core.repository.JobRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class JobExistsByIdValidator implements ConstraintValidator<JobExistsById, Long> {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return jobRepository.existsById(value);
    }

}
