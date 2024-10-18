package br.com.xfrontier.housekeeper.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xfrontier.housekeeper.api.dtos.responses.OportunityResponse;
import br.com.xfrontier.housekeeper.api.mappers.ApiOportunityMapper;
import br.com.xfrontier.housekeeper.core.repository.DailyRepository;
import br.com.xfrontier.housekeeper.core.utils.SecurityUtils;

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
