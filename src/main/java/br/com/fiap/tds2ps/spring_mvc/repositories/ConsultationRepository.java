package br.com.fiap.tds2ps.spring_mvc.repositories;

import br.com.fiap.tds2ps.spring_mvc.models.Consultation;
import br.com.fiap.tds2ps.spring_mvc.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByPatientOrderByDateTimeDesc(Patient patient);
    List<Consultation> findByPatient_CpfOrderByDateTimeDesc(String patientCpf);
}
