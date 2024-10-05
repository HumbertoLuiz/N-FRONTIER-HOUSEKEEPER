package com.learning.api.dtos.requests;

import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learning.core.validators.Age;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({"completeName", "email", "password", "passwordConfirmation", 
	                "userType", "cpf", "birth", "phoneNumber", "keyPix"})
public class UserRequest {

	@NotNull
	@Size(min = 3, max = 255)
	private String completeName;

	@NotNull
	@Size(max = 255)
	@Email
	private String email;

	@NotNull
	@NotEmpty
	private String password;

	@NotNull
	@NotEmpty
	private String passwordConfirmation;

	@NotNull
	private Integer userType;

	@NotNull
	@Size(min = 11, max = 11)
	@CPF
	private String cpf;

    @NotNull
	@Past
	@Age(min = 18, max = 100)
    @DateTimeFormat(iso= ISO.DATE)
	private LocalDate birth;

	@NotNull
	@Size(min = 11, max = 11)
	private String phoneNumber;

	@Size(min = 11, max = 255)
	private String keyPix;

	@NotNull
	private MultipartFile documentPicture;

	public UserRequest() {}

	public UserRequest(@NotNull @Size(min = 3, max = 255) String completeName,
			@NotNull @Size(max = 255) @Email String email,
			@NotNull @NotEmpty String password,
			@NotNull @NotEmpty String passwordConfirmation,
			@NotNull Integer userType,
			@NotNull @Size(min = 11, max = 11) @CPF String cpf,
			@NotNull @Past LocalDate birth,
			@NotNull @Size(min = 11, max = 11) String phoneNumber,
			@Size(min = 11, max = 255) String keyPix,
			@NotNull MultipartFile documentPicture) {
		this.completeName = completeName;
		this.email = email;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.userType = userType;
		this.cpf = cpf;
		this.birth = birth;
		this.phoneNumber = phoneNumber;
		this.keyPix = keyPix;
		this.documentPicture = documentPicture;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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

	public MultipartFile getDocumentPicture() {
		return documentPicture;
	}

	public void setDocumentPicture(MultipartFile documentPicture) {
		this.documentPicture = documentPicture;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birth, completeName, cpf, documentPicture, email,
				keyPix, password, passwordConfirmation, phoneNumber, userType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRequest other = (UserRequest) obj;
		return Objects.equals(birth, other.birth)
				&& Objects.equals(completeName, other.completeName)
				&& Objects.equals(cpf, other.cpf)
				&& Objects.equals(documentPicture, other.documentPicture)
				&& Objects.equals(email, other.email)
				&& Objects.equals(keyPix, other.keyPix)
				&& Objects.equals(password, other.password)
				&& Objects.equals(passwordConfirmation,
						other.passwordConfirmation)
				&& Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(userType, other.userType);
	}

}
