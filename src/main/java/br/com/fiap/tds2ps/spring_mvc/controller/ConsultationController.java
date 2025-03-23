package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.dto.ConsultationDto;
import br.com.fiap.tds2ps.spring_mvc.dto.MedicalRecord;
import br.com.fiap.tds2ps.spring_mvc.dto.PersonDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/consultation")
public class ConsultationController {

    //Validar se o paciente existe se existe nova consulta, caso contrário novo paciente
    @PostMapping("/start")
    public ModelAndView start(Model model, @ModelAttribute("patientLazy") PersonDto patient) {
        //Paciente já existe - no nosso vamos usar o cpf 12345678900 como ja existente e retorna ele
        if(patient.getCpf().equals("12345678900")){

            ConsultationDto consultation = new ConsultationDto(patient.getCpf(), "João da Silva");
            consultation.setHistoricoAtendimento("25/02/2025\nPaciente com histórico de diabetes");

            model.addAttribute("consultation", consultation);
            model.addAttribute("medicalRecord",new MedicalRecord());

            return new ModelAndView("add-consultation");
        }
        return new ModelAndView("add-patient");
    }

    @PostMapping("/save")
    public ModelAndView save(Model model, @ModelAttribute("medicalRecord") MedicalRecord medicalRecord) {
        System.out.println(medicalRecord.getAnamnese());
        System.out.println(medicalRecord.getPrescription());

        return new ModelAndView("redirect:/");
    }

}
