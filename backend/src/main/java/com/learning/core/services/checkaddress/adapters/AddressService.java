package com.learning.core.services.checkaddress.adapters;

import com.learning.core.services.checkaddress.dtos.AddressResponse;
import com.learning.core.services.checkaddress.exceptions.AddressServiceException;

public interface AddressService {

	AddressResponse findAddressByZipcode(String zipcode) throws AddressServiceException;

}
