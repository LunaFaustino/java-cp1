package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.dto.ConsultationDto;
import br.com.fiap.tds2ps.spring_mvc.dto.MedicalRecord;
import br.com.fiap.tds2ps.spring_mvc.dto.PersonDto;
import br.com.fiap.tds2ps.spring_mvc.models.Consultation;
import br.com.fiap.tds2ps.spring_mvc.models.Patient;
import br.com.fiap.tds2ps.spring_mvc.services.ConsultationService;
import br.com.fiap.tds2ps.spring_mvc.services.PatientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/consultation")
public class ConsultationController {

    private final PatientService patientService;
    private final ConsultationService consultationService;

    public ConsultationController(
            PatientService patientService,
            ConsultationService consultationService) {
        this.patientService = patientService;
        this.consultationService = consultationService;
    }

    @PostMapping("/start")
    public ModelAndView start(Model model, @ModelAttribute("patientLazy") PersonDto patient, HttpSession session) {
        Optional<Patient> patientOpt = patientService.findByCpf(patient.getCpf());

        if (patientOpt.isPresent()) {
            Patient existingPatient = patientOpt.get();
            String medicalStaffCpf = (String) session.getAttribute("loggedUserCpf");

            Consultation consultation = consultationService.startConsultation(existingPatient.getCpf(), medicalStaffCpf);

            ConsultationDto consultationDto = new ConsultationDto(existingPatient.getCpf(), existingPatient.getNome());
            consultationDto.setHistoricoAtendimento(existingPatient.getHistoricoAtendimento());

            model.addAttribute("consultation", consultationDto);
            model.addAttribute("medicalRecord", new MedicalRecord());
            model.addAttribute("consultationId", consultation.getId());

            return new ModelAndView("add-consultation");
        }

        model.addAttribute("patient", new Patient());
        return new ModelAndView("add-patient");
    }

    @PostMapping("/save")
    public ModelAndView save(
            Model model,
            @ModelAttribute("medicalRecord") MedicalRecord medicalRecord,
            @RequestParam("consultationId") Long consultationId) {

        Consultation finishedConsultation = consultationService.finishConsultation(consultationId, medicalRecord);

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/list")
    public ModelAndView listConsultations(Model model) {
        model.addAttribute("consultations", consultationService.findAll());
        return new ModelAndView("consultation-list");
    }
}
