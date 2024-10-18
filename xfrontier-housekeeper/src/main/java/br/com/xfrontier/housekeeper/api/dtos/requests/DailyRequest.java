package br.com.xfrontier.housekeeper.api.dtos.requests;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import br.com.xfrontier.housekeeper.core.validators.JobExistsById;
import br.com.xfrontier.housekeeper.core.validators.TimeAfter;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class DailyRequest {

    @NotNull
    @Future
    @TimeAfter(startTime = 6)
    private LocalDateTime dateService;

    @NotNull
    @Positive
    private Integer timeService;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @NotEmpty
    @Size(max = 60)
    private String address;

    @NotNull
    @NotEmpty
    @Size(max = 10)
    private String number;

    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String neighborhood;

    @Size(max = 100)
    private String complement;

    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String city;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 2)
    private String state;

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 8)
    private String zipCode;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String IbgeCode;

    @NotNull
    @PositiveOrZero
    private Integer quantityBedrooms;

    @NotNull
    @PositiveOrZero
    private Integer quantityRooms;

    @NotNull
    @PositiveOrZero
    private Integer quantityKitchens;

    @NotNull
    @PositiveOrZero
    private Integer quantityBathrooms;

    @NotNull
    @PositiveOrZero
    private Integer quantityYards;

    @NotNull
    @PositiveOrZero
    private Integer quantityOthers;

    @Size(max = 255)
    private String observations;

    @NotNull
    @Positive
    @JobExistsById
    private Long jobId;

}
