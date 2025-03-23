package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.dto.PersonDto;
import br.com.fiap.tds2ps.spring_mvc.models.MedicalStaff;
import br.com.fiap.tds2ps.spring_mvc.services.MedicalStaffService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class HomeController {

    private final MedicalStaffService medicalStaffService;

    public HomeController(MedicalStaffService medicalStaffService) {
        this.medicalStaffService = medicalStaffService;
    }

    @GetMapping("/")
    public ModelAndView index(Model model) {
        model.addAttribute("user", new PersonDto());
        return new ModelAndView("index");
    }

    @PostMapping("/sign-in")
    public ModelAndView signIn(Model model, @ModelAttribute("user") PersonDto user, HttpSession session) {
        Optional<MedicalStaff> medicalStaffOpt = medicalStaffService.findByCpf(user.getCpf());

        if (medicalStaffOpt.isPresent()) {
            MedicalStaff medicalStaff = medicalStaffOpt.get();
            session.setAttribute("loggedUserCpf", medicalStaff.getCpf());
            session.setAttribute("loggedUserName", medicalStaff.getNome());

            model.addAttribute("loggedAs", "Logged as " + medicalStaff.getCpf() + " - " + medicalStaff.getNome());
            model.addAttribute("patientLazy", new PersonDto());
            return new ModelAndView("home");
        } else {
            MedicalStaff newStaff = new MedicalStaff();
            newStaff.setCpf(user.getCpf());
            newStaff.setNome("Dr(a). " + user.getCpf());
            medicalStaffService.save(newStaff);

            session.setAttribute("loggedUserCpf", newStaff.getCpf());
            session.setAttribute("loggedUserName", newStaff.getNome());

            model.addAttribute("loggedAs", "Logged as " + newStaff.getCpf() + " - " + newStaff.getNome());
            model.addAttribute("patientLazy", new PersonDto());
            return new ModelAndView("home");
        }
    }
}
