package br.com.xfrontier.housekeeper.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.xfrontier.housekeeper.api.dtos.responses.AvailabilityResponse;
import br.com.xfrontier.housekeeper.api.dtos.responses.HousekeeperLocationPagedResponse;
import br.com.xfrontier.housekeeper.api.mappers.ApiHousekeeperMapper;
import br.com.xfrontier.housekeeper.core.models.User;
import br.com.xfrontier.housekeeper.core.repository.UserRepository;
import br.com.xfrontier.housekeeper.core.services.checkaddress.adapters.AddressService;

@Service
public class ApiHousekeeperService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ApiHousekeeperMapper mapper;

    @Autowired
    private AddressService addressService;

    public HousekeeperLocationPagedResponse findHousekeeperByzipCode(String zipCode) {

        var ibgeCode= findIbgeCodeByzipCode(zipCode);

        var userSort= Sort.sort(User.class);
        var sort= userSort.by(User::getReputation).descending();

        var pageNumber= 0;

        var pageSize= 6;

        var pageable= PageRequest.of(pageNumber, pageSize, sort);

        var result= repository.findByCitiesAttendedIbgeCode(ibgeCode, pageable);

        var housekeeper= result.getContent().stream().map(mapper::toHousekeeperLocationResponse)
            .toList();

        return new HousekeeperLocationPagedResponse(housekeeper, pageSize,
            result.getTotalElements());
    }

    public AvailabilityResponse checkAvailabilityByzipCode(String zipCode) {

        findIbgeCodeByzipCode(zipCode);

        var availability= repository.existsByCitiesAttendedIbgeCode(zipCode);

        return new AvailabilityResponse(availability);
    }

    private String findIbgeCodeByzipCode(String zipCode) {
        return addressService.findAddressByzipCode(zipCode).getIbge();
    }

}
