package org.example.tp_j2ee.repository;

import org.example.tp_j2ee.model.Consultation;
import org.hibernate.Session;

import java.util.List;

public class ConsultationRepository extends BaseRepository<Consultation> {
    public ConsultationRepository(Session session) {
        super(session);
    }

    @Override
    public Consultation findById(int id) {
        return _session.get(Consultation.class,id);
    }

    @Override
    public List<Consultation> findAll() {
        return _session.createQuery("from Consultation ").list();
    }
}
