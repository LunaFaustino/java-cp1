package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.dto.PersonDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView index(Model model) {
        model.addAttribute("user", new PersonDto());
        return new ModelAndView("index");
    }

    @PostMapping("/sign-in")
    public ModelAndView signIn(Model model, @ModelAttribute("user") PersonDto user) {
        model.addAttribute("loggedAs", "Logged as " + user.getCpf());
        model.addAttribute("patientLazy", new PersonDto());
        return new ModelAndView("home");
    }
}
