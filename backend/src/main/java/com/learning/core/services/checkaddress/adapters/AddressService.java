package com.learning.core.services.checkaddress.adapters;

import com.learning.core.services.checkaddress.dtos.AddressResponse;
import com.learning.core.services.checkaddress.exceptions.AddressServiceException;

public interface AddressService {
	
	AddressResponse findAddressByCep(String cep) throws AddressServiceException;

}
