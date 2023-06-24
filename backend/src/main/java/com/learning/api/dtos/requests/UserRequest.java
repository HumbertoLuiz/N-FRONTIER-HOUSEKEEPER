package com.learning.api.dtos.requests;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import com.learning.core.validators.Age;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public void setComplete_name(String completeName) {
        setCompleteName(completeName);
    }

    public void setPassword_confirmation(String passwordConfirmation) {
        setPasswordConfirmation(passwordConfirmation);
    }

    public void setUser_type(Integer userType) {
        setUserType(userType);
    }

    public void setKey_pix(String keyPix) {
        setKeyPix(keyPix);
    }
    
    public void setPhone_number(String phoneNumber) {
        setPhoneNumber(phoneNumber);
    }

    public void setDocument_picture(MultipartFile documentPicture) {
        setDocumentPicture(documentPicture);
    }
}
