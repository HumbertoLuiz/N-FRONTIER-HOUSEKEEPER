package br.com.xfrontier.housekeeper.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xfrontier.housekeeper.api.dtos.requests.HousekeeperAddressRequest;
import br.com.xfrontier.housekeeper.api.dtos.responses.HousekeeperAddressResponse;
import br.com.xfrontier.housekeeper.api.mappers.ApiHousekeeperAddressMapper;
import br.com.xfrontier.housekeeper.core.exceptions.HousekeeperAddressNotFoundException;
import br.com.xfrontier.housekeeper.core.repository.UserRepository;
import br.com.xfrontier.housekeeper.core.utils.SecurityUtils;

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
