package br.com.xfrontier.housekeeper.web.dtos;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({"completeName", "email"})
public class UserUpdateForm {

    @NotNull
    @Size(min = 3, max = 255)
    private String completeName;

    @NotNull
    @Size(min = 3, max = 255)
    @Email
    private String email;

    public UserUpdateForm() {}
    
	public UserUpdateForm(
			@NotNull @Size(min = 3, max = 255) String completeName,
			@NotNull @Size(min = 3, max = 255) @Email String email) {
		this.completeName = completeName;
		this.email = email;
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

	@Override
	public int hashCode() {
		return Objects.hash(completeName, email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserUpdateForm other = (UserUpdateForm) obj;
		return Objects.equals(completeName, other.completeName)
				&& Objects.equals(email, other.email);
	}

}
