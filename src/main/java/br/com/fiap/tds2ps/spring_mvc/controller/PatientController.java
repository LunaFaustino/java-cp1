package br.com.fiap.tds2ps.spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @PostMapping("/save")
    public ModelAndView addPatient() {
        return new ModelAndView("add-consultation");
    }
}
