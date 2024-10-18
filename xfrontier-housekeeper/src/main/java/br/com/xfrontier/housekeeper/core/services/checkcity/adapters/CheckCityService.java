package br.com.xfrontier.housekeeper.core.services.checkcity.adapters;

import br.com.xfrontier.housekeeper.core.services.checkcity.dtos.CityResponse;
import br.com.xfrontier.housekeeper.core.services.checkcity.exceptions.CheckCityServiceException;

public interface CheckCityService {

    CityResponse findCityByIbgeCode(String ibgeCode) throws CheckCityServiceException;

}