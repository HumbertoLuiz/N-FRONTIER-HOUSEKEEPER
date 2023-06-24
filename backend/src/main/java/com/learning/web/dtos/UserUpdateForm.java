package com.learning.web.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateForm {

    @NotNull
    @Size(min = 3, max = 255)
    private String completeName;

    @NotNull
    @Size(min = 3, max = 255)
    @Email
    private String email;

}
