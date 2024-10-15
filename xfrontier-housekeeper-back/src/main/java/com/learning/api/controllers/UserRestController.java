package com.learning.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learning.api.assembler.UserAssembler;
import com.learning.api.dtos.requests.UpdateUserRequest;
import com.learning.api.dtos.requests.UserRequest;
import com.learning.api.dtos.responses.MessageResponse;
import com.learning.api.dtos.responses.UserResponse;
import com.learning.api.services.ApiUserService;
import com.learning.core.permissions.EHousekeeperPermissions;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

	@Autowired
	private ApiUserService service;

    @Autowired
    private UserAssembler assembler;
    
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
    public UserResponse save(@ModelAttribute @Valid UserRequest request) {
        var response = service.save(request);
        assembler.addLinks(response);
        return response;
	}
	

    @PutMapping
    @EHousekeeperPermissions.isHousekeeperOrCustomer
    public MessageResponse update(@RequestBody @Valid UpdateUserRequest request) {
        return service.update(request);
    }

    @PostMapping("/picture")
    @EHousekeeperPermissions.isHousekeeperOrCustomer
    public MessageResponse updateUserPicture(
        @RequestPart("user_picture") MultipartFile userPicture
    ) {
        return service.updateUserPicture(userPicture);
    }
}
