package com.learning.web.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.learning.core.enums.Icon;
import com.learning.web.dtos.FlashMessage;
import com.learning.web.dtos.JobForm;
import com.learning.web.services.WebJobService;

@Controller
@RequestMapping("/admin/jobs")
public class WebJobController {

	@Autowired
	private WebJobService webJobService;

    @GetMapping
    public ModelAndView findAll() {
        var modelAndView = new ModelAndView("/admin/job/list");

        modelAndView.addObject("jobs", webJobService.findAll());

        return modelAndView;
    }

    @GetMapping("/insert")
    public ModelAndView insert() {
        var modelAndView = new ModelAndView("/admin/job/form");

        modelAndView.addObject("form", new JobForm());

        return modelAndView;
    }

    @PostMapping("/insert")
    public String insert(@Valid @ModelAttribute("form") JobForm form, BindingResult result, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            return "/admin/job/form";
        }

        webJobService.insert(form);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Service registered with success!"));

        return "redirect:/admin/jobs";
    }

    @GetMapping("/{id}/update")
    public ModelAndView update(@PathVariable Long id) {
        var modelAndView = new ModelAndView("/admin/job/form");

        modelAndView.addObject("form", webJobService.findById(id));

        return modelAndView;
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("form") JobForm form, BindingResult result, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            return "admin/job/form";
        }

        webJobService.update(form, id);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Service edited with success!"));

        return "redirect:/admin/jobs";
    }

    @GetMapping("/{id}/delete")
    public String excluir(@PathVariable Long id, RedirectAttributes attrs) {
    	webJobService.deleteById(id);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Service deleted with success!"));

        return "redirect:/admin/jobs";
    }

    @ModelAttribute("icons")
    public Icon[] getIcons() {
        return Icon.values();
    }
}
