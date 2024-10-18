package br.com.xfrontier.housekeeper.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.xfrontier.housekeeper.api.controllers.ConfirmationPresenceRestController;
import br.com.xfrontier.housekeeper.api.controllers.DailyCancellationRestController;
import br.com.xfrontier.housekeeper.api.controllers.DailyPaymentRestController;
import br.com.xfrontier.housekeeper.api.controllers.DailyRestController;
import br.com.xfrontier.housekeeper.api.controllers.RatingRestController;
import br.com.xfrontier.housekeeper.api.dtos.responses.DailyResponse;
import br.com.xfrontier.housekeeper.core.repository.RatingRepository;
import br.com.xfrontier.housekeeper.core.utils.SecurityUtils;

@Component
public class DailyAssembler implements Assembler<DailyResponse> {

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public void addLinks(DailyResponse resource) {
        var id = resource.getId();
        if (securityUtils.isCustomer() && resource.isNoPayment()) {
            var payDailyLink = linkTo(methodOn(DailyPaymentRestController.class).pay(null, id))
                .withRel("pay_daily")
                .withType("POST");

            resource.addLinks(payDailyLink);
        }

        if (isAbleForPresenceConfirmation(resource)) {
            var confirmPresenceLink = linkTo(methodOn(ConfirmationPresenceRestController.class).confirmPresence(id))
                .withRel("confirm_housekeeper")
                .withType("PATCH");

            resource.addLinks(confirmPresenceLink);
        }

        if (isAbleForRating(resource)) {
            var ratingLink = linkTo(methodOn(RatingRestController.class).rateDaily(null, id))
                .withRel("avaliar_diaria")
                .withType("PATCH");
            resource.addLinks(ratingLink);
        }

        if (isAbleForCancellation(resource)) {
            var cancelarDailyLink = linkTo(methodOn(DailyCancellationRestController.class).cancel(id, null))
                .withRel("cancelar_diaria")
                .withType("PATCH");
            resource.addLinks(cancelarDailyLink);
        }

        var selfLink = linkTo(methodOn(DailyRestController.class).findById(id))
            .withSelfRel()
            .withType("GET");

        resource.addLinks(selfLink);
    }

    @Override
    public void addLinks(List<DailyResponse> collectionResource) {
        collectionResource.stream()
            .forEach(this::addLinks);
    }

    private boolean isAbleForPresenceConfirmation(DailyResponse resource) {
        return resource.isConfirmed()
            && isDateServiceInPast(resource)
            && resource.getHousekeeper() != null;
    }

    private boolean isDateServiceInPast(DailyResponse resource) {
        return resource.getDateService().isBefore(LocalDateTime.now());
    }

    private boolean isAbleForRating(DailyResponse resource) {
        return resource.isConcluded()
            && !ratingRepository.existsByEvaluatorAndDailyId(securityUtils.getLoggedUser(), resource.getId());
    }

    private boolean isAbleForCancellation(DailyResponse resource) {
        return (resource.isPaid() || resource.isConfirmed())
            && resource.getDateService().isAfter(LocalDateTime.now());
    }

}
