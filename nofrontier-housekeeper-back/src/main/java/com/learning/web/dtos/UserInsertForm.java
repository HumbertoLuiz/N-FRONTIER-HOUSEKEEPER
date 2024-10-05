package com.learning.web.dtos;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learning.web.interfaces.IConfirmPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({"completeName", "email", "password", "confirmPassword"})
public class UserInsertForm implements IConfirmPassword {

	@NotNull
	@Size(min = 3, max = 255)
	private String completeName;

	@NotNull
	@Size(min = 3, max = 255)
	@Email
	private String email;

	@NotNull
	@NotEmpty
	private String password;

	@NotNull
	@NotEmpty
	private String confirmPassword;
	
		
	public UserInsertForm() {}
	

	public UserInsertForm(
			@NotNull @Size(min = 3, max = 255) String completeName,
			@NotNull @Size(min = 3, max = 255) @Email String email,
			@NotNull @NotEmpty String password,
			@NotNull @NotEmpty String confirmPassword) {
		this.completeName = completeName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public int hashCode() {
		return Objects.hash(completeName, confirmPassword, email, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInsertForm other = (UserInsertForm) obj;
		return Objects.equals(completeName, other.completeName)
				&& Objects.equals(confirmPassword, other.confirmPassword)
				&& Objects.equals(email, other.email)
				&& Objects.equals(password, other.password);
	}

}
