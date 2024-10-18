package br.com.xfrontier.housekeeper.api.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityAttendedResponse {

    private Long id;
    private String city;
    private String state;
    private String ibgeCode;

}
