package com.learning.core.services.checkaddress.providers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.learning.core.services.checkaddress.adapters.AddressService;
import com.learning.core.services.checkaddress.dtos.AddressResponse;
import com.learning.core.services.checkaddress.exceptions.AddressServiceException;

@Service
public class ViaCepService implements AddressService {

    private static final String URL_TEMPLATE = "http://viacep.com.br/ws/{cep}/json/";
    private final RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public AddressResponse findAddressByCep(String cep) throws AddressServiceException {
		var url = UriComponentsBuilder.fromUriString(URL_TEMPLATE)
			.buildAndExpand(cep)
			.toString();
		
		ResponseEntity<AddressResponse> response;
		try {
			response = restTemplate.getForEntity(url, AddressResponse.class);
		}catch(HttpClientErrorException.BadRequest e) {
			throw new AddressServiceException("The zip code entered is not valid");
		}
		
		if (response.getBody().getCep() == null) {
			throw new AddressServiceException("the zip code entered not found");
		}
		
		return response.getBody();
	}

}
