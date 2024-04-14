package com.learning.web.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learning.web.interfaces.IConfirmPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonNaming(SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
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

}
