package com.learning.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.learning.api.dtos.response.HousekeeperLocationPagedResponse;
import com.learning.api.mappers.ApiHousekeeperMapper;
import com.learning.core.models.User;
import com.learning.core.repository.UserRepository;
import com.learning.core.services.checkaddress.adapters.AddressService;

@Service
public class ApiHousekeeperService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ApiHousekeeperMapper mapper;

	@Autowired
	private AddressService addressService;

	public HousekeeperLocationPagedResponse findHousekeeperByCep(String cep) {

		var ibgeCode = addressService.findAddressByCep(cep).getIbge();

		var userSort = Sort.sort(User.class);
		var sort = userSort.by(User::getReputation).descending();

		var pageNumber = 0;

		var pageSize = 6;

		var pageable = PageRequest.of(pageNumber, pageSize, sort);

		var result = repository.findByCitiesAttendedIbgeCode(ibgeCode, pageable);

		var housekeeper = result.getContent().stream().map(mapper::toHousekeeperLocationResponse).toList();

		return new HousekeeperLocationPagedResponse(housekeeper, pageSize, result.getTotalElements());
	}

}