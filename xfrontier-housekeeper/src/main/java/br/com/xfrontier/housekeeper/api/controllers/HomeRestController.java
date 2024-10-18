package br.com.xfrontier.housekeeper.api.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xfrontier.housekeeper.api.dtos.responses.HateoasResponse;

@RestController
@RequestMapping("/api")
public class HomeRestController {

    @GetMapping
    public HateoasResponse home() {
        var listJobsLink= linkTo(methodOn(JobRestController.class).findAll())
            .withRel("listr_jobs")
            .withType("GET");
        var addresszipCodeLink= linkTo(methodOn(AddressRestController.class).findAddressByzipCode(null))
            .withRel("address_zipCode")
            .expand()
            .withType("GET");
        var housekeeperLocationLink= linkTo(
            methodOn(HousekeeperRestController.class).findHousekeeperByzipCode(null))
                .withRel("housekeeper_location")
                .expand()
                .withType("GET");
        var checkAvailabilityAttendLink= linkTo(
            methodOn(HousekeeperRestController.class).checkAvailabilityByzipCode(null))
                .withRel("check_availability_attend")
                .expand()
                .withType("GET");

        var userRegisterLink= linkTo(methodOn(UserRestController.class).save(null))
            .withRel("user_register")
            .withRel("POST");
        var loginLink= linkTo(methodOn(AuthRestController.class).authenticate(null))
            .withRel("login")
            .withRel("POST");
        var refreshLink= linkTo(methodOn(AuthRestController.class).reauthenticate(null))
            .withRel("refresh")
            .withRel("POST");
        var logoutLink= linkTo(methodOn(AuthRestController.class).logout(null))
            .withRel("logout")
            .withRel("POST");
        var loggedUserLink= linkTo(methodOn(MeRestController.class).me())
            .withRel("loogged_user")
            .withRel("GET");

        var response= new HateoasResponse();

        response.addLinks(
            listJobsLink,
            addresszipCodeLink,
            housekeeperLocationLink,
            checkAvailabilityAttendLink,
            userRegisterLink,
            loginLink,
            refreshLink,
            logoutLink,
            loggedUserLink);

        return response;
    }
}
