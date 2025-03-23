package br.com.fiap.tds2ps.spring_mvc.models;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tb_cp1_pessoa")
public class Person {

    @Id
    private String cpf;

    @Column(name = "nome", nullable = false)
    private String nome;

    public Person() {
    }

    public Person(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
