package br.com.xfrontier.housekeeper.web.services.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import br.com.xfrontier.housekeeper.core.exceptions.PasswordDoesntMatchException;
import br.com.xfrontier.housekeeper.core.services.PasswordResetService;
import br.com.xfrontier.housekeeper.web.dtos.ResetConfirmPasswordForm;
import br.com.xfrontier.housekeeper.web.dtos.ResetPasswordForm;
import br.com.xfrontier.housekeeper.web.services.WebResetPasswordService;

@Service 
public class WebResetPasswordServiceImpl implements WebResetPasswordService {

    @Autowired
    private PasswordResetService passwordResetService;

    public void requestResetPassword(ResetPasswordForm form) {
        var passwordReset = passwordResetService.createPasswordReset(form.getEmail());

        if (passwordReset != null) {
            var props = new HashMap<String, Object>();
            props.put("/admin/reset-password/confirm?token=" + passwordReset.getToken(), props);
        }
    }

    public void confirmResetPassword(String token, ResetConfirmPasswordForm form) {
        validateConfirmPassword(form);
        passwordResetService.resetPassword(token, form.getPassword());
    }

    private void validateConfirmPassword(ResetConfirmPasswordForm form) {
        var password = form.getPassword();
        var confirmPassword = form.getConfirmPassword();

        if (!password.equals(confirmPassword)) {
            var message = "The two fields doesn't match";
            var fieldError = new FieldError(form.getClass().getName(), "confirmPassword", form.getConfirmPassword(), false, null, null, message);
            throw new PasswordDoesntMatchException(message, fieldError);
        }
    }

}
