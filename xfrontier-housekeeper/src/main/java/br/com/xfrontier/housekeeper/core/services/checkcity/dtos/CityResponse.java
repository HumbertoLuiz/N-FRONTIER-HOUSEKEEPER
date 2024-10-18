package br.com.xfrontier.housekeeper.core.services.checkcity.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityResponse {

    private String ibgeCode;

    private String city;

    private String state;

}