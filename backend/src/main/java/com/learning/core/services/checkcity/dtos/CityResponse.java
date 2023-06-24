package com.learning.core.services.checkcity.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityResponse {

    private String ibge;

    private String cidade;

    private String estado;

}