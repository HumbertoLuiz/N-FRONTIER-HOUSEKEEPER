package com.learning.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.api.dtos.requests.CitiesAttendedRequest;
import com.learning.api.dtos.responses.CityAttendedResponse;
import com.learning.api.dtos.responses.MessageResponse;
import com.learning.api.mappers.ApiCityAttendedMapper;
import com.learning.core.exceptions.CityAttendedNotFoundException;
import com.learning.core.models.CityAttended;
import com.learning.core.repository.CityAttendedRepository;
import com.learning.core.repository.UserRepository;
import com.learning.core.services.checkcity.adapters.CheckCityService;
import com.learning.core.utils.SecurityUtils;

@Service
public class ApiCitiesAttendedService {

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private ApiCityAttendedMapper mapper;

    @Autowired
    private CityAttendedRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckCityService checkCityService;

    public List<CityAttendedResponse> listCitiesAttended() {
        return securityUtils.getLoggedUser()
            .getCitiesAttended()
            .stream()
            .map(mapper::toResponse)
            .toList();
    }

    public MessageResponse updateCitiesAttended(CitiesAttendedRequest request) {
        var loggedUser = securityUtils.getLoggedUser();
        var citiesAttended = new ArrayList<CityAttended>();

        request.getCities().forEach(cityAttendedRequest -> {
            var ibgeCode = cityAttendedRequest.getIbgeCode();
            CityAttended cityAttended;
            try {
            	cityAttended = findCityAttendedByIbgeCode(ibgeCode);
            } catch (CityAttendedNotFoundException exception) {
            	cityAttended = registerCityAttended(ibgeCode);
            }
            citiesAttended.add(cityAttended);
        });
        loggedUser.setCitiesAttended(citiesAttended);
        userRepository.save(loggedUser);
        return new MessageResponse("Cities served successfully updated!");
    }

    private CityAttended registerCityAttended(String ibgeCode) {
        var city = checkCityService.findCityByIbgeCode(ibgeCode);
        var cidyAttended = new CityAttended();
        cidyAttended.setIbgeCode(ibgeCode);
        cidyAttended.setCity(city.getCity());
        cidyAttended.setState(city.getState());

        return repository.save(cidyAttended);
    }

    private CityAttended findCityAttendedByIbgeCode(String ibgeCode) {
        var message = String.format("City with ibge code %s not found", ibgeCode);
        return repository.findByIbgeCode(ibgeCode)
            .orElseThrow(() -> new CityAttendedNotFoundException(message));
    }

}
