package br.com.xfrontier.housekeeper.api.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import br.com.xfrontier.housekeeper.api.dtos.requests.ResetPasswordConfirmationRequest;
import br.com.xfrontier.housekeeper.api.dtos.requests.ResetPasswordRequest;
import br.com.xfrontier.housekeeper.api.dtos.responses.MessageResponse;
import br.com.xfrontier.housekeeper.core.exceptions.PasswordDoesntMatchException;
import br.com.xfrontier.housekeeper.core.services.PasswordResetService;
import br.com.xfrontier.housekeeper.core.services.email.adapters.EmailService;
import br.com.xfrontier.housekeeper.core.services.email.dtos.EmailParams;


@Service
public class ApiResetPasswordService {

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private EmailService emailService;

    @Value("${br.com.xfrontier.housekeeper.frontend.host}")
    private String hostFrontend;

    public MessageResponse requestPasswordReset(ResetPasswordRequest request) {
        var passwordReset = passwordResetService.createPasswordReset(request.getEmail());

        if (passwordReset != null) {
            var props = new HashMap<String, Object>();
            props.put("link", hostFrontend + "/recover-password?token=" + passwordReset.getToken());
            var emailParams = EmailParams.builder()
                .addressee(request.getEmail())
                .subject("Password reset request")
                .template("emails/reset-password")
                .props(props)
                .build();
            emailService.sendMailTemplateHtml((EmailParams) emailParams);
        }

        return new MessageResponse("Check your e-mail for the password reset link");
    }

    public MessageResponse confirmPasswordReset(ResetPasswordConfirmationRequest request) {
    	validatePasswordConfirmation(request);
        passwordResetService.resetPassword(request.getToken(), request.getPassword());
        return new MessageResponse("Password changed successfully!");
    }

    private void validatePasswordConfirmation(ResetPasswordConfirmationRequest request) {
        var password = request.getPassword();
        var passwordConfirmation = request.getPasswordConfirmation();

        if (!password.equals(passwordConfirmation)) {
            var message = "The two password fields don't match";
            var fieldError = new FieldError(request.getClass().getName(), "confirmPassword", request.getPasswordConfirmation(), false, null, null, message);
            throw new PasswordDoesntMatchException(message, fieldError);
        }
    }

}
