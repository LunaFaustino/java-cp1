package br.com.fiap.tds2ps.spring_mvc.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cp1_paciente")
public class Patient extends Person {

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cep")
    private String cep;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "genero")
    private String genero;

    @Column(name = "historico_atendimento", columnDefinition = "CLOB")
    private String historicoAtendimento;

    public Patient() {
    }

    public Patient(String cpf, String nome) {
        super(cpf, nome);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getHistoricoAtendimento() {
        return historicoAtendimento;
    }

    public void setHistoricoAtendimento(String historicoAtendimento) {
        this.historicoAtendimento = historicoAtendimento;
    }
}
