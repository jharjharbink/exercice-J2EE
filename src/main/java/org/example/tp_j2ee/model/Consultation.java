package org.example.tp_j2ee.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Consultation {

    @Id
    @GeneratedValue
    private int id;

    private LocalDate date;
    private String doctorName;
    private String prescription;
    private String careSheet;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    public Consultation() {}
    public Consultation(LocalDate date, String doctorName, String prescription, String careSheet) {
        this.date = date;
        this.doctorName = doctorName;
        this.prescription = prescription;
        this.careSheet = careSheet;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String description) {
        this.prescription = description;
    }

    public String getCareSheet() {
        return careSheet;
    }

    public void setCareSheet(String careSheet) {
        this.careSheet = careSheet;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
