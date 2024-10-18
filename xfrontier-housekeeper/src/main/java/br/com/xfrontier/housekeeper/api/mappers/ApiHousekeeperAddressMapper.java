package br.com.xfrontier.housekeeper.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.xfrontier.housekeeper.api.dtos.requests.HousekeeperAddressRequest;
import br.com.xfrontier.housekeeper.api.dtos.responses.HousekeeperAddressResponse;
import br.com.xfrontier.housekeeper.core.models.HousekeeperAddress;

@Mapper(componentModel = "spring")
public interface ApiHousekeeperAddressMapper {

	ApiHousekeeperAddressMapper INSTANCE = Mappers.getMapper(ApiHousekeeperAddressMapper.class);

    HousekeeperAddress toModel(HousekeeperAddressRequest request);

    HousekeeperAddressResponse toResponse(HousekeeperAddress model);

}
