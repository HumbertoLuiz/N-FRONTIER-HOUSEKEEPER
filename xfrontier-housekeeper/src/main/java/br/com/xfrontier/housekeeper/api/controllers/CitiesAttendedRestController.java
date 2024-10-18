package br.com.xfrontier.housekeeper.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xfrontier.housekeeper.api.dtos.requests.CitiesAttendedRequest;
import br.com.xfrontier.housekeeper.api.dtos.responses.CityAttendedResponse;
import br.com.xfrontier.housekeeper.api.dtos.responses.MessageResponse;
import br.com.xfrontier.housekeeper.api.services.ApiCitiesAttendedService;
import br.com.xfrontier.housekeeper.core.permissions.EHousekeeperPermissions;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios/cidades-atendidas")
public class CitiesAttendedRestController {

    @Autowired
    private ApiCitiesAttendedService service;

    @GetMapping
    @EHousekeeperPermissions.isHousekeeperOrCustomer
    public List<CityAttendedResponse> listCitiesAttended() {
        return service.listCitiesAttended();
    }

    @PutMapping
    @EHousekeeperPermissions.isHousekeeperOrCustomer
    public MessageResponse updateCitiesAttended(@RequestBody @Valid CitiesAttendedRequest request) {
        return service.updateCitiesAttended(request);
    }

}
