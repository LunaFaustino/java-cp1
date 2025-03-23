package br.com.fiap.tds2ps.spring_mvc.services;

import br.com.fiap.tds2ps.spring_mvc.dto.PersonDto;
import br.com.fiap.tds2ps.spring_mvc.models.MedicalStaff;
import br.com.fiap.tds2ps.spring_mvc.repositories.MedicalStaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalStaffService {

    private final MedicalStaffRepository medicalStaffRepository;

    public MedicalStaffService(MedicalStaffRepository medicalStaffRepository) {
        this.medicalStaffRepository = medicalStaffRepository;
    }

    public List<MedicalStaff> findAll() {
        return medicalStaffRepository.findAll();
    }

    public Optional<MedicalStaff> findByCpf(String cpf) {
        return medicalStaffRepository.findById(cpf);
    }

    public MedicalStaff save(MedicalStaff medicalStaff) {
        return medicalStaffRepository.save(medicalStaff);
    }

    public void deleteByCpf(String cpf) {
        medicalStaffRepository.deleteById(cpf);
    }

    public boolean existsByCpf(String cpf) {
        return medicalStaffRepository.existsById(cpf);
    }

    public boolean authenticate(PersonDto personDto) {
        return existsByCpf(personDto.getCpf());
    }

    public MedicalStaff fromPersonDto(PersonDto personDto) {
        MedicalStaff medicalStaff = new MedicalStaff();
        medicalStaff.setCpf(personDto.getCpf());
        medicalStaff.setNome(personDto.getNome());
        return medicalStaff;
    }
}
