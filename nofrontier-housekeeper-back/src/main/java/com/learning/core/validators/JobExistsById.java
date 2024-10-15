package com.learning.core.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = JobExistsByIdValidator.class)
public @interface JobExistsById {

    String message() default "service with id ${validatedValue} does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
