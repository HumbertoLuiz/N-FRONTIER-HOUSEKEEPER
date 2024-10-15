package com.learning.core.validators;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import com.learning.core.exceptions.ValidatingException;
import com.learning.core.models.Daily;
import com.learning.core.utils.SecurityUtils;

@Component
public class ApplicationValidator {

    @Autowired
    private SecurityUtils securityUtils;

    public void validate(Daily daily) {
    	validateApplicantAddress(daily);
    }

    private void validateApplicantAddress(Daily daily) {
        var applicant = securityUtils.getLoggedUser();

        if (applicant.getAddress() == null) {
            var message = "Diarist must have registered address";
            var fieldError = new FieldError(daily.getClass().getName(), "applicants", null, false, null, null, message);
            throw new ValidatingException(message, fieldError);
        }

        validateDuplicityApplicant(daily);
    }

    private void validateDuplicityApplicant(Daily daily) {
        var applicant = securityUtils.getLoggedUser();
        var applicants = daily.getApplicants();

        if (applicants.contains(applicant)) {
            var message = "Diarist has already applied for this daily rate";
            var fieldError = new FieldError(daily.getClass().getName(), "applicants", null, false, null, null, message);
            throw new ValidatingException(message, fieldError);
        }

        validateApplicantQuantity(daily);
    }

    private void validateApplicantQuantity(Daily daily) {
        var applicants = daily.getApplicants();

        if (applicants.size() >= 3) {
            var message = "Daily already has the maximum number of applicants";
            var fieldError = new FieldError(daily.getClass().getName(), "applicants", null, false, null, null, message);
            throw new ValidatingException(message, fieldError);
        }

        validateDailyStatus(daily);
    }

    private void validateDailyStatus(Daily daily) {
        if (!daily.isPaid()) {
            var message = "Daily rate not in PAID status";
            var fieldError = new FieldError(daily.getClass().getName(), "status", null, false, null, null, message);
            throw new ValidatingException(message, fieldError);
        }

        validateDayDateService(daily);
    }

    private void validateDayDateService(Daily daily) {
        var today = LocalDateTime.now();
        var dateService = daily.getDateService();

        if (today.isAfter(dateService)) {
            var message = "Daily rate with date of service in the past";
            var fieldError = new FieldError(daily.getClass().getName(), "dateService", null, false, null, null, message);
            throw new ValidatingException(message, fieldError);
        }

        validarHousekeeperDaily(daily);
    }

    private void validarHousekeeperDaily(Daily daily) {
        var housekeeper = daily.getHousekeeper();

        if (housekeeper != null) {
            var message = "You already have a day laborer";
            var fieldError = new FieldError(daily.getClass().getName(), "housekeeper", null, false, null, null, message);
            throw new ValidatingException(message, fieldError);
        }
    }

}
