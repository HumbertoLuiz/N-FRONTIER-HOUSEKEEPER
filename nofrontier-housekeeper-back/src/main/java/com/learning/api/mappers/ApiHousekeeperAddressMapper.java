package com.learning.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.learning.api.dtos.requests.HousekeeperAddressRequest;
import com.learning.api.dtos.responses.HousekeeperAddressResponse;
import com.learning.core.models.HousekeeperAddress;

@Mapper(componentModel = "spring")
public interface ApiHousekeeperAddressMapper {

	ApiHousekeeperAddressMapper INSTANCE = Mappers.getMapper(ApiHousekeeperAddressMapper.class);

    HousekeeperAddress toModel(HousekeeperAddressRequest request);

    HousekeeperAddressResponse toResponse(HousekeeperAddress model);

}
