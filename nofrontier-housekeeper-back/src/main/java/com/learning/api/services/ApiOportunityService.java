package com.learning.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.api.dtos.responses.OportunityResponse;
import com.learning.api.mappers.ApiOportunityMapper;
import com.learning.core.repository.DailyRepository;
import com.learning.core.utils.SecurityUtils;

@Service
public class ApiOportunityService {

    @Autowired
    private DailyRepository repository;

    @Autowired
    private ApiOportunityMapper mapper;

    @Autowired
    private SecurityUtils securityUtils;

    public List<OportunityResponse> findOportunities() {
        var userLogged = securityUtils.getLoggedUser();
        var cities = userLogged.getCitiesAttended()
            .stream()
            .map(city -> city.getIbgeCode())
            .toList();
        return repository.findOpportunities(cities, userLogged)
            .stream()
            .map(mapper::toResponse)
            .toList();
    }

}
