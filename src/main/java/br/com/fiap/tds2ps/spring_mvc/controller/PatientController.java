package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.dto.ConsultationDto;
import br.com.fiap.tds2ps.spring_mvc.dto.MedicalRecord;
import br.com.fiap.tds2ps.spring_mvc.models.Consultation;
import br.com.fiap.tds2ps.spring_mvc.models.Patient;
import br.com.fiap.tds2ps.spring_mvc.services.ConsultationService;
import br.com.fiap.tds2ps.spring_mvc.services.PatientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;
    private final ConsultationService consultationService;

    public PatientController(
            PatientService patientService,
            ConsultationService consultationService) {
        this.patientService = patientService;
        this.consultationService = consultationService;
    }

    @PostMapping("/save")
    public ModelAndView addPatient(@ModelAttribute("patient") Patient patient,
                                   Model model,
                                   HttpSession session) {

        Patient savedPatient = patientService.save(patient);

        String medicalStaffCpf = (String) session.getAttribute("loggedUserCpf");
        Consultation consultation = consultationService.startConsultation(savedPatient.getCpf(), medicalStaffCpf);

        ConsultationDto consultationDto = new ConsultationDto(savedPatient.getCpf(), savedPatient.getNome());
        consultationDto.setHistoricoAtendimento(savedPatient.getHistoricoAtendimento());

        model.addAttribute("consultation", consultationDto);
        model.addAttribute("medicalRecord", new MedicalRecord());
        model.addAttribute("consultationId", consultation.getId());

        return new ModelAndView("add-consultation");
    }

    @GetMapping("/list")
    public ModelAndView listPatients(Model model) {
        model.addAttribute("patients", patientService.findAll());
        return new ModelAndView("patient-list");
    }
}
