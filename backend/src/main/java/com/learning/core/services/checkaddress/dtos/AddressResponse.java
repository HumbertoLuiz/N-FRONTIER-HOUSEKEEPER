package com.learning.core.services.checkaddress.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

	private String zipcode;

	private String address;

	private String complement;

	private String neighborhood;

	private String lacation;

	private String state;

	private String ibge;

}
