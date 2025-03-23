package br.com.fiap.tds2ps.spring_mvc.services;

import br.com.fiap.tds2ps.spring_mvc.dto.ConsultationDto;
import br.com.fiap.tds2ps.spring_mvc.dto.MedicalRecord;
import br.com.fiap.tds2ps.spring_mvc.models.Consultation;
import br.com.fiap.tds2ps.spring_mvc.models.MedicalStaff;
import br.com.fiap.tds2ps.spring_mvc.models.Patient;
import br.com.fiap.tds2ps.spring_mvc.repositories.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final PatientService patientService;
    private final MedicalStaffService medicalStaffService;

    public ConsultationService(ConsultationRepository consultationRepository, PatientService patientService, MedicalStaffService medicalStaffService) {
        this.consultationRepository = consultationRepository;
        this.patientService = patientService;
        this.medicalStaffService = medicalStaffService;
    }

    public List<Consultation> findAll() {
        return consultationRepository.findAll();
    }

    public Optional<Consultation> findById(Long id) {
        return consultationRepository.findById(id);
    }

    public Consultation save(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    public void deleteById(Long id) {
        consultationRepository.deleteById(id);
    }

    public List<Consultation> findByPatientCpf(String patientCpf) {
        return consultationRepository.findByPatient_CpfOrderByDateTimeDesc(patientCpf);
    }

    public Consultation startConsultation(String patientCpf, String medicalStaffCpf) {
        Optional<Patient> patientOpt = patientService.findByCpf(patientCpf);
        Optional<MedicalStaff> medicalStaffOpt = medicalStaffService.findByCpf(medicalStaffCpf);

        if (patientOpt.isPresent() && medicalStaffOpt.isPresent()) {
            Consultation consultation = Consultation.createConsultation(
                    patientOpt.get(),
                    medicalStaffOpt.get(),
                    LocalDateTime.now(),
                    Consultation.ConsultationStatus.EM_ANDAMENTO
            );

            return consultationRepository.save(consultation);
        }

        throw new IllegalArgumentException("Patient or Medical Staff not found");
    }

    public Consultation finishConsultation(Long consultationId, MedicalRecord medicalRecord) {
        Optional<Consultation> consultationOpt = consultationRepository.findById(consultationId);

        if (consultationOpt.isPresent()) {
            Consultation consultation = consultationOpt.get();
            consultation.setAnamnese(medicalRecord.getAnamnese());
            consultation.setPrescription(medicalRecord.getPrescription());
            consultation.setStatus(Consultation.ConsultationStatus.FINALIZADA);

            Patient patient = consultation.getPatient();
            String currentHistory = patient.getHistoricoAtendimento();
            String dateStr = LocalDateTime.now().toString().substring(0, 10);

            String newEntry = dateStr + "\n" + medicalRecord.getAnamnese() + "\n\n";

            if (currentHistory != null && !currentHistory.isEmpty()) {
                patient.setHistoricoAtendimento(currentHistory + "\n" + newEntry);
            } else {
                patient.setHistoricoAtendimento(newEntry);
            }

            patientService.save(patient);
            return consultationRepository.save(consultation);
        }

        throw new IllegalArgumentException("Consultation not found");
    }

    public ConsultationDto toDto(Consultation consultation) {
        Patient patient = consultation.getPatient();
        ConsultationDto dto = new ConsultationDto(patient.getCpf(), patient.getNome());
        dto.setHistoricoAtendimento(patient.getHistoricoAtendimento());
        return dto;
    }
}
