package br.com.fiap.tds2ps.spring_mvc.dto;

public class MedicalRecord extends PersonDto {

    private String anamnese, prescription;

    public String getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(String anamnese) {
        this.anamnese = anamnese;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }
}
