package com.learning.api.dtos.responses;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learning.core.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonNaming(SnakeCaseStrategy.class)
public class UserResponse {

	private Long id;
	private String completeName;
	private String email;
	private Integer userType;
	private String cpf;
	private LocalDate birth;
	private String phoneNumber;
	private String keyPix;
    private String userPicture;

	@JsonIgnore
	public Boolean isCustomer() {
		return userType.equals(UserType.CUSTOMER.getId());
	}
}
