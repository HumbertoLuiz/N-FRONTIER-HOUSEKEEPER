package com.learning.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.stereotype.Component;

import com.learning.api.controllers.ApplicationRestController;
import com.learning.api.dtos.responses.OportunityResponse;

@Component
public class OportunityAssembler implements Assembler<OportunityResponse> {

    @Override
    public void addLinks(OportunityResponse resource) {
        var id = resource.getId();

        var applyDailyLink = linkTo(methodOn(ApplicationRestController.class).apply(id))
            .withRel("apply_daily")
            .withType("POST");

        resource.addLinks(applyDailyLink);
    }

    @Override
    public void addLinks(List<OportunityResponse> collectionResource) {
        collectionResource.stream()
            .forEach(this::addLinks);
    }


}
