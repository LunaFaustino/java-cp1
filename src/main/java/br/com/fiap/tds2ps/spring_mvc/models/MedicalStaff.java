package br.com.fiap.tds2ps.spring_mvc.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cp1_profissional")
public class MedicalStaff extends Person {

    @Column(name = "registro_profissional")
    private String registroProfissional;

    public MedicalStaff() {
    }

    public MedicalStaff(String cpf, String nome) {
        super(cpf, nome);
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }
}
