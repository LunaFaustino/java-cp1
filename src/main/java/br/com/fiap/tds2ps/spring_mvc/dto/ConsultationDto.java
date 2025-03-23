package br.com.fiap.tds2ps.spring_mvc.dto;

public class ConsultationDto extends PersonDto {

    private String historicoAtendimento;

    public ConsultationDto() {
    }

    public ConsultationDto(String cpf, String nome) {
        super(cpf, nome);
    }

    public String getHistoricoAtendimento() {
        return historicoAtendimento;
    }

    public void setHistoricoAtendimento(String historicoAtendimento) {
        this.historicoAtendimento = historicoAtendimento;
    }
}
