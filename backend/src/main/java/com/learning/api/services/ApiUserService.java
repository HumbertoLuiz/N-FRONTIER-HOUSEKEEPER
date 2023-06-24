package com.learning.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import com.learning.api.dtos.requests.UserRequest;
import com.learning.api.dtos.responses.TokenResponse;
import com.learning.api.dtos.responses.UserRegisterResponse;
import com.learning.api.dtos.responses.UserResponse;
import com.learning.api.mappers.ApiUserMapper;
import com.learning.core.exceptions.PasswordDoesntMatchException;
import com.learning.core.publishers.NewUserPublisher;
import com.learning.core.repository.UserRepository;
import com.learning.core.services.storage.adapters.StorageService;
import com.learning.core.services.token.adapters.TokenService;
import com.learning.core.validators.UserValidator;

@Service
public class ApiUserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ApiUserMapper mapper;

    @Autowired
    private TokenService tokenService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserValidator validator;

    @Autowired
    private StorageService storageService;

    @Autowired
    private NewUserPublisher newUserPublisher;

	public UserResponse save(UserRequest request) {
		validatePasswordConfirmation(request);
		var userToSave = mapper.toModel(request);
		validator.validate(userToSave);
		var passwordEncrypted = passwordEncoder.encode(userToSave.getPassword());
		userToSave.setPassword(passwordEncrypted);
        var documentPicture= storageService.save(request.getDocumentPicture());
        userToSave.setDocumentPicture(documentPicture);

        if (userToSave.isHousekeeper()) {
            var averageReputation= calculateHousekepperAverageReputation();
            userToSave.setReputation(averageReputation);
        }

		var userSaved = repository.save(userToSave);
        newUserPublisher.publish(userSaved);
        var response= mapper.toRegisterResponse(userSaved);
        var tokenResponse= generateTokenResponse(response);
        response.setToken(tokenResponse);
        return response;
	}

    private Double calculateHousekepperAverageReputation() {
        var averageReputation= repository.getHousekeeperAverageReputation();
        if (averageReputation == null || averageReputation == 0.0) {
            averageReputation= 5.0;
        }
        return averageReputation;
    }

    private void validatePasswordConfirmation(UserRequest request) {
		var password = request.getPassword();
		var passwordConfirmation = request.getPasswordConfirmation();
		if (!password.equals(passwordConfirmation)) {
			var message = "the two password fields do not match";
			var fieldError = new FieldError(request.getClass().getName(), "passwordConfirmation",
					request.getPasswordConfirmation(), false, null, null, message);
			throw new PasswordDoesntMatchException(message, fieldError);
		}
	}

    private TokenResponse generateTokenResponse(UserRegisterResponse response) {
        var token= tokenService.generateAccessToken(response.getEmail());
        var refresh= tokenService.generateRefreshToken(response.getEmail());
        var tokenResponse= new TokenResponse(token, refresh);
        return tokenResponse;
    }
}
