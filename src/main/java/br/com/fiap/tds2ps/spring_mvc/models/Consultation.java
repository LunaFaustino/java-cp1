package br.com.fiap.tds2ps.spring_mvc.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_cp1_consulta")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_consulta")
    @SequenceGenerator(name = "seq_consulta", sequenceName = "sq_consulta", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_cpf", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "profissional_cpf", nullable = false)
    private MedicalStaff medicalStaff;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "anamnese", columnDefinition = "CLOB")
    private String anamnese;

    @Column(name = "prescription", columnDefinition = "CLOB")
    private String prescription;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ConsultationStatus status;

    public enum ConsultationStatus {
        AGENDADA, EM_ANDAMENTO, FINALIZADA, CANCELADA
    }

    public Consultation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public MedicalStaff getMedicalStaff() {
        return medicalStaff;
    }

    public void setMedicalStaff(MedicalStaff medicalStaff) {
        this.medicalStaff = medicalStaff;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

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

    public ConsultationStatus getStatus() {
        return status;
    }

    public void setStatus(ConsultationStatus status) {
        this.status = status;
    }

    public static Consultation createConsultation(Patient patient, MedicalStaff medicalStaff, LocalDateTime dateTime, ConsultationStatus status) {
        Consultation consultation = new Consultation();
        consultation.setPatient(patient);
        consultation.setMedicalStaff(medicalStaff);
        consultation.setDateTime(dateTime);
        consultation.setStatus(status);
        return consultation;
    }}
