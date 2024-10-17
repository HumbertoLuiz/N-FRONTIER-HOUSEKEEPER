package com.learning.api.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learning.core.enums.DailyStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonNaming(SnakeCaseStrategy.class)
public class DailyResponse extends HateoasResponse {

    private Long id;

    private String jobName;
    
    private LocalDateTime dateService;

    private Integer timeService;

    private Integer status;

    private BigDecimal price;

    private BigDecimal valueCommission;

    private String address;

    private String number;

    private String neighborhood;

    private String complement;

    private String city;

    private String state;

    private String zipCode;

    private String ibgeCode;

    private Integer quantityBedrooms;

    private Integer quantityRooms;

    private Integer quantityKitchens;

    private Integer quantityBathrooms;

    private Integer quantityYards;

    private Integer quantityOthers;

    private String observations;

    private String reasonCancellation;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long job;

    private UserDailyResponse customer;

    private UserDailyResponse housekeeper;

    @JsonIgnore
    public Boolean isNoPayment() {
        return status.equals(DailyStatus.NO_PAYMENT.getId());
    }

    @JsonIgnore
    public Boolean isConfirmed() {
        return status.equals(DailyStatus.CONFIRMED.getId());
    }

    @JsonIgnore
    public Boolean isConcluded() {
        return status.equals(DailyStatus.CONCLUDED.getId());
    }

    @JsonIgnore
    public boolean isPaid() {
        return status.equals(DailyStatus.PAID.getId());
    }

}
