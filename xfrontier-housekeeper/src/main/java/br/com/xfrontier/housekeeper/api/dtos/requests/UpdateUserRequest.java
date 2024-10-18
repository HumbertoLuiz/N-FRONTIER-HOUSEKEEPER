package br.com.xfrontier.housekeeper.api.dtos.requests;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import br.com.xfrontier.housekeeper.core.validators.Age;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class UpdateUserRequest {

    @Size(min = 3, max = 255)
    private String completeName;

    @Email
    @Size(max = 255)
    private String email;

    @CPF
    @Size(min = 11, max = 11)
    private String cpf;

    @Past
    @Age(min = 18, max = 100)
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate birth;

    @Size(min = 11, max = 11)
    private String phoneNumber;

    @Size(min = 11, max = 255)
    private String keyPix;

    @Size(max = 255)
    private String password;

    @Size(max = 255)
    private String newPassword;

    @Size(max = 255)
    private String passwordConfirmation;

}
