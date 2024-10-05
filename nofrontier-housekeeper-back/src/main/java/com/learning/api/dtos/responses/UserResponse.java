package com.learning.api.dtos.responses;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learning.core.enums.UserType;

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

	public UserResponse() {}
	
	public UserResponse(Long id, String completeName, String email,
			Integer userType, String cpf, LocalDate birth, String phoneNumber,
			String keyPix, String userPicture) {
		this.id = id;
		this.completeName = completeName;
		this.email = email;
		this.userType = userType;
		this.cpf = cpf;
		this.birth = birth;
		this.phoneNumber = phoneNumber;
		this.keyPix = keyPix;
		this.userPicture = userPicture;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompleteName() {
		return completeName;
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getKeyPix() {
		return keyPix;
	}

	public void setKeyPix(String keyPix) {
		this.keyPix = keyPix;
	}

	public String getUserPicture() {
		return userPicture;
	}

	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birth, completeName, cpf, email, id, keyPix,
				phoneNumber, userPicture, userType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserResponse other = (UserResponse) obj;
		return Objects.equals(birth, other.birth)
				&& Objects.equals(completeName, other.completeName)
				&& Objects.equals(cpf, other.cpf)
				&& Objects.equals(email, other.email)
				&& Objects.equals(id, other.id)
				&& Objects.equals(keyPix, other.keyPix)
				&& Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(userPicture, other.userPicture)
				&& Objects.equals(userType, other.userType);
	}
	
}
