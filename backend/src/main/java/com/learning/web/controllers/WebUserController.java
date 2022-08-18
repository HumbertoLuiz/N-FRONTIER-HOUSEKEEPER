package com.learning.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learning.core.exceptions.ValidationException;
import com.learning.web.dtos.FlashMessage;
import com.learning.web.dtos.UserInsertForm;
import com.learning.web.services.WebUserService;

@Controller
@RequestMapping("/admin/users")
public class WebUserController {

	@Autowired
	private WebUserService webUserService;
	
    @GetMapping
    public ModelAndView findAll() {
        var modelAndView = new ModelAndView("/admin/user/list");

        modelAndView.addObject("users", webUserService.findAll());

        return modelAndView;
    }
    
    @GetMapping("/insert")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("admin/user/insert-form");

        modelAndView.addObject("insertForm", new UserInsertForm());

        return modelAndView;
    }

    @PostMapping("/insert")
    public String cadastrar(@Valid @ModelAttribute("insertForm") UserInsertForm insertForm, BindingResult result, RedirectAttributes attrs
    ) {
        if (result.hasErrors()) {
            return "admin/user/insert-form";
        }

        try {
            webUserService.insert(insertForm);
            attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "User registered with success!"));
        } catch (ValidationException e) {
            result.addError(e.getFieldError());
            return "admin/user/insert-form";
        }

        return "redirect:/admin/users";
    }
}
