package org.example.tp_j2ee.repository;

import org.example.tp_j2ee.model.Consultation;
import org.example.tp_j2ee.model.Patient;
import org.hibernate.Session;

import java.util.List;

public class PatientRepository extends BaseRepository<Patient> {
    public PatientRepository(Session session) {
        super(session);
    }

    @Override
    public Patient findById(int id) {
        return _session.get(Patient.class,id);
    }

    @Override
    public List<Patient> findAll() {
        return _session.createQuery("from Patient ").list();
    }
}
