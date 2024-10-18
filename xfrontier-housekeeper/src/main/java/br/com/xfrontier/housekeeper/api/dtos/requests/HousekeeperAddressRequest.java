package br.com.xfrontier.housekeeper.api.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HousekeeperAddressRequest {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 60)
    private String address;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 10)
    private String number;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30)
    private String bairro;

    @Size(max = 255)
    private String complement;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 8)
    private String zipCode;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30)
    private String city;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 2)
    private String state;

}
