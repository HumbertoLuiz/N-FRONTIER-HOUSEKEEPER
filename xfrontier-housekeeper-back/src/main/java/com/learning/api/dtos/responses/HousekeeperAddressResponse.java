package com.learning.api.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HousekeeperAddressResponse {

    private Long id;
    private String address;
    private String number;
    private String neighborhood;
    private String complement;
    private String zipCode;
    private String city;
    private String state;

}
