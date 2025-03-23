package br.com.fiap.tds2ps.spring_mvc.dto;

public class PersonDto {

    private String cpf, nome;

    public PersonDto() {
    }

    public PersonDto(String cpf, String nome) {
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
