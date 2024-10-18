package br.com.xfrontier.housekeeper.api.services;

import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import br.com.xfrontier.housekeeper.api.dtos.requests.UpdateUserRequest;
import br.com.xfrontier.housekeeper.api.dtos.requests.UserRequest;
import br.com.xfrontier.housekeeper.api.dtos.responses.MessageResponse;
import br.com.xfrontier.housekeeper.api.dtos.responses.TokenResponse;
import br.com.xfrontier.housekeeper.api.dtos.responses.UserRegisterResponse;
import br.com.xfrontier.housekeeper.api.dtos.responses.UserResponse;
import br.com.xfrontier.housekeeper.api.mappers.ApiUserMapper;
import br.com.xfrontier.housekeeper.core.exceptions.IncorrectPasswordException;
import br.com.xfrontier.housekeeper.core.exceptions.PasswordDoesntMatchException;
import br.com.xfrontier.housekeeper.core.models.User;
import br.com.xfrontier.housekeeper.core.publishers.NewUserPublisher;
import br.com.xfrontier.housekeeper.core.repository.UserRepository;
import br.com.xfrontier.housekeeper.core.services.storage.adapters.StorageService;
import br.com.xfrontier.housekeeper.core.services.token.adapters.TokenService;
import br.com.xfrontier.housekeeper.core.utils.SecurityUtils;
import br.com.xfrontier.housekeeper.core.validators.UserValidator;

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
    private SecurityUtils securityUtils;

    @Autowired
    private StorageService storageService;

    @Autowired
    private NewUserPublisher newUserPublisher;

    public UserResponse save(UserRequest request) {
        validatePasswordConfirmation(request);
        var userToSave= mapper.toModel(request);
        validator.validate(userToSave);
        var passwordEncrypted= passwordEncoder.encode(userToSave.getPassword());
        userToSave.setPassword(passwordEncrypted);
        var documentPicture= storageService.save(request.getDocumentPicture());
        userToSave.setDocumentPicture(documentPicture);

        if (userToSave.isHousekeeper()) {
            var averageReputation= calculateHousekepperAverageReputation();
            userToSave.setReputation(averageReputation);
        }

        var userSaved= repository.save(userToSave);
        newUserPublisher.publish(userSaved);
        var response= mapper.toRegisterResponse(userSaved);
        var tokenResponse= generateTokenResponse(response);
        response.setToken(tokenResponse);
        return response;
    }
    
    public MessageResponse updateUserPicture(MultipartFile userPicture) {
        var loggedUser = securityUtils.getLoggedUser();
        var picture = storageService.save((MultipartFile) loggedUser);
        loggedUser.setUserPicture(picture);
        repository.save(loggedUser);
        return new MessageResponse("Photo saved successfully!");
    }

    public MessageResponse update(UpdateUserRequest request) {
        var loggedUser = securityUtils.getLoggedUser();

        updateLoggedInUserInformation(request, loggedUser);

        validator.validate(loggedUser);

        updatePassword(request, loggedUser);

        repository.save(loggedUser);

        return new MessageResponse("User successfully updated");
    }

    private void updatePassword(UpdateUserRequest request, User loggedUser) {
        var hasPassword = request.getPassword() != null
            && request.getNewPassword() != null
            && request.getPasswordConfirmation() != null;

        if (hasPassword) {
            checkPassword(request, loggedUser);
            validatePasswordConfirmation(request);
            var newPassword = request.getNewPassword();
            var newPasswordHash = passwordEncoder.encode(newPassword);
            loggedUser.setPassword(newPasswordHash);
        }
    }

    private void checkPassword(UpdateUserRequest request, User loggedUser) {
        var passwordRequest = request.getPassword();
        var senhaDB = loggedUser.getPassword();

        if (!passwordEncoder.matches(passwordRequest, senhaDB)) {
            var message = "The password you entered is incorrect";
            var fieldError = new FieldError(request.getClass().getName(), "password", passwordRequest, false, null, null, message);
            throw new IncorrectPasswordException(message, fieldError);
        }
    }

    private void updateLoggedInUserInformation(UpdateUserRequest request, User loggedUser) {    	
    	loggedUser.setCompleteName(firstNonNull(request.getCompleteName(), loggedUser.getCompleteName()));
    	loggedUser.setEmail(firstNonNull(request.getEmail(), loggedUser.getEmail()));
    	loggedUser.setCpf(firstNonNull(request.getCpf(), loggedUser.getCpf()));
    	loggedUser.setBirth(firstNonNull(request.getBirth(), loggedUser.getBirth()));
    	loggedUser.setPhoneNumber(firstNonNull(request.getPhoneNumber(), loggedUser.getPhoneNumber()));
    	loggedUser.setKeyPix(firstNonNull(request.getKeyPix(), loggedUser.getKeyPix()));
    }
    
    private TokenResponse generateTokenResponse(UserRegisterResponse response) {
        var token= tokenService.generateAccessToken(response.getEmail());
        var refresh= tokenService.generateRefreshToken(response.getEmail());
        var tokenResponse= new TokenResponse(token, refresh);
        return tokenResponse;
    }
    
    private Double calculateHousekepperAverageReputation() {
        var averageReputation= repository.getHousekeeperAverageReputation();
        if (averageReputation == null || averageReputation == 0.0) {
            averageReputation= 5.0;
        }
        return averageReputation;
    }

    private void validatePasswordConfirmation(UserRequest request) {
        var password= request.getPassword();
        var passwordConfirmation= request.getPasswordConfirmation();
        if (!password.equals(passwordConfirmation)) {
            var message= "the two password fields do not match";
            var fieldError= new FieldError(request.getClass().getName(), "passwordConfirmation",
                request.getPasswordConfirmation(), false, null, null, message);
            throw new PasswordDoesntMatchException(message, fieldError);
        }
    }

    private void validatePasswordConfirmation(UpdateUserRequest request) {
        var password = request.getNewPassword();
        var passwordConfirmation = request.getPasswordConfirmation();

        if (!password.equals(passwordConfirmation)) {
            var message = "The two password fields don't match";
            var fieldError = new FieldError(request.getClass().getName(), "passwordConfirmation", request.getPasswordConfirmation(), false, null, null, message);

            throw new PasswordDoesntMatchException(message, fieldError);
        }
    }
}
