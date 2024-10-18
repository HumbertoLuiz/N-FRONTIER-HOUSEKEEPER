package br.com.xfrontier.housekeeper.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.xfrontier.housekeeper.api.controllers.CitiesAttendedRestController;
import br.com.xfrontier.housekeeper.api.controllers.DailyRestController;
import br.com.xfrontier.housekeeper.api.controllers.HousekeeperAddressRestController;
import br.com.xfrontier.housekeeper.api.controllers.OportunityRestController;
import br.com.xfrontier.housekeeper.api.controllers.PaymentRestController;
import br.com.xfrontier.housekeeper.api.controllers.UserRestController;
import br.com.xfrontier.housekeeper.api.dtos.responses.UserResponse;

@Component
public class UserAssembler implements Assembler<UserResponse> {

    @Override
    public void addLinks(UserResponse resource) {
        if (resource.isCustomer()) {
            var registerDailyLink = linkTo(methodOn(DailyRestController.class).register(null))
                .withRel("register_daily")
                .withType("POST");

            resource.addLinks(registerDailyLink);
        } else {
            var updateAddressLink = linkTo(methodOn(HousekeeperAddressRestController.class).updateAddress(null))
                .withRel("update_address")
                .withType("PUT");

            var listAddressLink = linkTo(methodOn(HousekeeperAddressRestController.class).showAddress())
                .withRel("list_address")
                .withType("GET");

            var connectCitiesLink = linkTo(methodOn(CitiesAttendedRestController.class).updateCitiesAttended(null))
                .withRel("connect_cities")
                .withType("PUT");

            var citiesAttendedLink = linkTo(methodOn(CitiesAttendedRestController.class).listCitiesAttended())
                .withRel("cities_attended")
                .withType("GET");

            var listOportunitiesLink = linkTo(methodOn(OportunityRestController.class).findOportunities())
                .withRel("list_oportunities")
                .withType("GET");

            var listPaymentLink = linkTo(methodOn(PaymentRestController.class).listPayment())
                .withRel("list_payment")
                .withType("GET");

            resource.addLinks(
                updateAddressLink,
                listAddressLink,
                connectCitiesLink,
                citiesAttendedLink,
                listOportunitiesLink,
                listPaymentLink
            );
        }

        var listDailyLink = linkTo(methodOn(DailyRestController.class).listByLoggedUser())
            .withRel("lista_diarias")
            .withType("GET");

        var updateUserPictureLink = linkTo(methodOn(UserRestController.class).updateUserPicture(null))
            .withRel("alterar_foto_usuario")
            .withType("POST");

        var editUserLink = linkTo(methodOn(UserRestController.class).update(null))
            .withRel("editar_usuario")
            .withType("PUT");

        resource.addLinks(listDailyLink, updateUserPictureLink, editUserLink);
    }

    @Override
    public void addLinks(List<UserResponse> collectionResource) {}

}
