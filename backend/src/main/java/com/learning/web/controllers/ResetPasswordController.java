package com.learning.web.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.learning.core.exceptions.ValidatingException;
import com.learning.web.dtos.FlashMessage;
import com.learning.web.dtos.ResetConfirmPasswordForm;
import com.learning.web.dtos.ResetPasswordForm;
import com.learning.web.services.WebResetPasswordService;


@Controller
@RequestMapping("/admin/reset-password")
public class ResetPasswordController {

    @Autowired
    private WebResetPasswordService service;

    @GetMapping
    public ModelAndView resetarSenha() {
        var modelAndView = new ModelAndView("admin/auth/reset-password");

        modelAndView.addObject("form", new ResetPasswordForm());

        return modelAndView;
    }

    @PostMapping
    public String resetPassword(@ModelAttribute ResetPasswordForm form, RedirectAttributes attrs) {
        //service.solictarResetDeSenha(form);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Verifique o seu e-mail para ter acesso ao link de reset de senha"));
        return "redirect:/admin/reset-password";
    }

    @GetMapping("/confirm")
    public ModelAndView confirmResetPassword() {
        var modelAndView = new ModelAndView("admin/auth/confirm-reset-password");
        modelAndView.addObject("form", new ResetConfirmPasswordForm());
        return modelAndView;
    }

    @PostMapping("/confirm")
    public String confirmResetPassword (
        @ModelAttribute("form") @Valid ResetConfirmPasswordForm form,
        BindingResult result,
        @RequestParam String token
    ) {
        try {
            service.confirmResetPassword(token, form);
        } catch (ValidatingException e) {
            result.addError(e.getFieldError());
            return "admin/auth/confirm-reset-password";
        }
        return "redirect:/admin/login";
    }
}

