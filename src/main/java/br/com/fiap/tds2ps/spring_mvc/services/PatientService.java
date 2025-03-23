package br.com.fiap.tds2ps.spring_mvc.services;

import br.com.fiap.tds2ps.spring_mvc.dto.PersonDto;
import br.com.fiap.tds2ps.spring_mvc.models.Patient;
import br.com.fiap.tds2ps.spring_mvc.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    public Optional<Patient> findByCpf(String cpf){
        return patientRepository.findById(cpf);
    }

    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }

    public void deleteByCpf(String cpf){
        patientRepository.deleteById(cpf);
    }

    public boolean existsByCpf(String cpf){
        return patientRepository.existsById(cpf);
    }

    public Patient fromPersonDto(PersonDto personDto){
        Patient patient = new Patient();
        patient.setCpf(personDto.getCpf());
        patient.setNome(personDto.getNome());
        return patient;
    }
}
