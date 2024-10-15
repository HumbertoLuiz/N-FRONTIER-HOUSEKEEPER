package com.learning.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.api.dtos.requests.HousekeeperAddressRequest;
import com.learning.api.dtos.responses.HousekeeperAddressResponse;
import com.learning.api.mappers.ApiHousekeeperAddressMapper;
import com.learning.core.exceptions.HousekeeperAddressNotFoundException;
import com.learning.core.repository.UserRepository;
import com.learning.core.utils.SecurityUtils;

@Service
public class ApiHousekeeperAddressService {

    @Autowired
    private ApiHousekeeperAddressMapper mapper;

    @Autowired
    private UserRepository repository;

    @Autowired
    private SecurityUtils securityUtils;

    public HousekeeperAddressResponse updateAddress(HousekeeperAddressRequest request) {
        var userLogged = securityUtils.getLoggedUser();

        var address = mapper.toModel(request);
        userLogged.setAddress(address);

        repository.save(userLogged);

        return mapper.toResponse(userLogged.getAddress());
    }

    public HousekeeperAddressResponse showAddress() {
        var userLogged = securityUtils.getLoggedUser();
        var address = userLogged.getAddress();

        if (address == null) {
            var message = String.format("User address %s not found", userLogged.getEmail());
            throw new HousekeeperAddressNotFoundException(message);
        }
        return mapper.toResponse(address);
    }

}
