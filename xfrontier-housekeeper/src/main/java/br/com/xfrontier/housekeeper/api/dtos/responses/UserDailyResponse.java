package br.com.xfrontier.housekeeper.api.dtos.responses;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class UserDailyResponse {

    private Long id;

    private String completeName;

    private LocalDate birth;

    private String userPicture;

    private String Phone;

    private Integer userType;

    private Double reputation;

}
