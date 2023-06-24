// package com.learning.api.controllers;

// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.learning.api.dtos.responses.HateoasResponse;

// @RestController
// @RequestMapping("/api")
// public class HomeRestController {

// 	@GetMapping
// 	public HateoasResponse home() {
// 		var listJobsLink = linkTo(methodOn(JobRestController.class).findAll())
// 			.withRel("listr_jobs")
// 			.withType("GET");
// 		var addressCepLink = linkTo(methodOn(AddressRestController.class).findAddressByCep(null))
// 			.withRel("address_cep")
// 			.expand()
// 			.withType("GET");
// 		var housekeeperLocationLink = linkTo(methodOn(HousekeeperRestController.class).findHousekeeperByCep(null))
// 			.withRel("housekeeper_location")
// 			.expand()
// 			.withType("GET");
// 		var checkAvailabilityAttendLink = linkTo(methodOn(HousekeeperRestController.class).checkAvailabilityByCep(null))
// 			.withRel("check_availability_attend")
// 			.expand()
// 			.withType("GET");

// 		var response = new HateoasResponse();

// 		response.addLinks(
// 			listJobsLink,
// 			addressCepLink,
// 			housekeeperLocationLink,
// 			checkAvailabilityAttendLink
// 		);

// 		return response;
// 	}
// }
