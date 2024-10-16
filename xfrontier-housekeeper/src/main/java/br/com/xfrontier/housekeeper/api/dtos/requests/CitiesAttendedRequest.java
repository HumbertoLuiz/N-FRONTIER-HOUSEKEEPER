package br.com.xfrontier.housekeeper.api.dtos.requests;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitiesAttendedRequest {

    private List<CityAttendedRequest> cities;

}
