package com.learning.core.services.checkcity.adapters;

import com.learning.core.services.checkcity.dtos.CityResponse;
import com.learning.core.services.checkcity.exceptions.CheckCityServiceException;

public interface CheckCityService {

    CityResponse findCityByIbgeCode(String ibgeCode) throws CheckCityServiceException;

}