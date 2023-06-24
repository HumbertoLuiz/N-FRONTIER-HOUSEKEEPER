package com.learning.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learning.api.dtos.requests.UserRequest;
import com.learning.api.dtos.responses.UserResponse;
import com.learning.api.services.ApiUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

	@Autowired
	private ApiUserService service;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
    public UserResponse save(@ModelAttribute @Valid UserRequest request) {
		return service.save(request);
	}
}
