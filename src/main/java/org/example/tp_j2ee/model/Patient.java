package org.example.tp_j2ee.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String telephone;
    private String photoRelativePath;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)  // , orphanRemoval = true
    private List<Consultation> consultations;

    public Patient() {}
    public Patient(String name, String surname, String photoRelativePath) {
        this.name = name;
        this.telephone = surname;
        this.photoRelativePath = photoRelativePath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String surname) {
        this.telephone = surname;
    }

    public String getPhotoRelativePath() {
        return photoRelativePath;
    }

    public void setPhotoRelativePath(String photoRelativePath) {
        this.photoRelativePath = photoRelativePath;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
}
