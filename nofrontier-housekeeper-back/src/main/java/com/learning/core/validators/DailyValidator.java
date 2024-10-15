package com.learning.core.validators;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import com.learning.core.exceptions.ValidatingException;
import com.learning.core.models.Daily;
import com.learning.core.repository.UserRepository;
import com.learning.core.services.checkaddress.adapters.AddressService;
import com.learning.core.services.checkaddress.exceptions.AddressServiceException;

@Component
public class DailyValidator {

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserRepository userRepository;

    public void validate(Daily daily) {
    	validateEndTime(daily);
    }

    private void validateEndTime(Daily daily) {
        var dateService = daily.getDateService();
        var timeService = daily.getTimeService();
        var EndDate = dateService.plusHours(timeService);
        var dateLimit = dateService.withHour(22).withMinute(0).withSecond(0);

        if (EndDate.isAfter(dateLimit)) {
            var message = "it can't be after 10pm";
            var fieldError = new FieldError(daily.getClass().getName(), "dateService", daily.getDateService(), false, null, null, message);

            throw new ValidatingException(message, fieldError);
        }

        validateServiceTime(daily);
    }

    private void validateServiceTime(Daily daily) {
        var timeService = daily.getTimeService();
        var totalTime = calculateTotalTime(daily);

        if (timeService != totalTime) {
            var message = "values do not match";
            var fieldError = new FieldError(daily.getClass().getName(), "timeService", daily.getTimeService(), false, null, null, message);

            throw new ValidatingException(message, fieldError);
        }

        validatePrice(daily);
    }

    private void validatePrice(Daily daily) {
        var price = daily.getPrice();
        var totalValue = calculateTotalValue(daily);

        if (price.compareTo(totalValue) != 0) {
            var message = "values do not match";
            var fieldError = new FieldError(daily.getClass().getName(), "price", daily.getPrice(), false, null, null, message);

            throw new ValidatingException(message, fieldError);
        }

        validateZipCode(daily);
    }

    private void validateZipCode(Daily daily) {
        var zipCode = daily.getZipCode();

        try {
            addressService.findAddressByzipCode(zipCode);
        } catch (AddressServiceException exception) {
            var message = exception.getLocalizedMessage();
            var fieldError = new FieldError(daily.getClass().getName(), "zipCode", daily.getZipCode(), false, null, null, message);

            throw new ValidatingException(message, fieldError);
        }

        validateIbgeCode(daily);
    }

    private void validateIbgeCode(Daily daily) {
        var zipCode = daily.getZipCode();
        var ibgeCode = daily.getIbgeCode();
        var ibgeCodeValid = addressService.findAddressByzipCode(zipCode).getIbge();

        if (!ibgeCode.equals(ibgeCodeValid)) {
            var message = "invalid ibge code";
            var fieldError = new FieldError(daily.getClass().getName(), "ibgeCode", daily.getIbgeCode(), false, null, null, message);

            throw new ValidatingException(message, fieldError);
        }

        validateAvailability(daily);
    }

    private void validateAvailability(Daily daily) {
        var ibgeCode = daily.getIbgeCode();
        var availability = userRepository.existsByCitiesAttendedIbgeCode(ibgeCode);
        if (!availability) {
            var message = "there are no day laborers at the zip code provided";
            var fieldError = new FieldError(daily.getClass().getName(), "zipCode", daily.getZipCode(), false, null, null, message);

            throw new ValidatingException(message, fieldError);
        }

        validateDateService(daily);
    }

    private void validateDateService(Daily daily) {
        var dateService = daily.getDateService();
        var minDate = LocalDateTime.now().plusHours(48);

        if (dateService.isBefore(minDate)) {
            var message = "must be at least 48 hours old from the current date";
            var fieldError = new FieldError(daily.getClass().getName(), "dateSErvice", daily.getDateService(), false, null, null, message);

            throw new ValidatingException(message, fieldError);
        }
    }

    private BigDecimal calculateTotalValue(Daily daily) {
        var job = daily.getJob();
        var minAmount = job.getMinAmount();

        var valueBedroom = calculateRoomValue(job.getBedroomAmount(), daily.getQuantityBedrooms());
        var roomValue = calculateRoomValue(job.getRoomAmount(), daily.getQuantityRooms());
        var valueKitchen = calculateRoomValue(job.getKitchenAmount(), daily.getQuantityKitchens());
        var valueBathroom = calculateRoomValue(job.getBathroomAmount(), daily.getQuantityBathrooms());
        var valueYard = calculateRoomValue(job.getYardAmount(), daily.getQuantityYards());
        var valueOthers = calculateRoomValue(job.getOthersAmount(), daily.getQuantityOthers());

        var totalValue = valueBedroom
        	.add(roomValue)
            .add(valueBathroom)
            .add(valueKitchen)
            .add(valueYard)
            .add(valueOthers);

        if (totalValue.compareTo(minAmount) < 0) {
            return minAmount;
        }
        return totalValue;
    }

    private BigDecimal calculateRoomValue(BigDecimal roomValue, Integer numberOfRooms) {
        return roomValue.multiply(new BigDecimal(numberOfRooms));
    }

    private Integer calculateTotalTime(Daily daily) {
        var job = daily.getJob();

        Integer totalTime = 0;
        totalTime += daily.getQuantityBedrooms() * job.getBedroomHours();
        totalTime += daily.getQuantityRooms() * job.getRoomHours();
        totalTime += daily.getQuantityKitchens() * job.getKitchenHours();
        totalTime += daily.getQuantityBathrooms() * job.getBathroomHours();
        totalTime += daily.getQuantityYards() * job.getYardHours();
        totalTime += daily.getQuantityOthers() * job.getOthersHours();

        return totalTime;
    }

}
