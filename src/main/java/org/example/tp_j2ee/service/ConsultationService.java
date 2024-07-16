package org.example.tp_j2ee.service;

import org.example.tp_j2ee.model.Consultation;
import org.example.tp_j2ee.repository.ConsultationRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ConsultationService {

    private ConsultationRepository consultationRepository;
    private SessionFactory _sessionFactory;
    private Session session;

    public ConsultationService(SessionFactory _sessionFactory) {
        _sessionFactory = _sessionFactory;

    }

    public Consultation getConsultation(int id){
        Consultation consultation = null;
        session = _sessionFactory.openSession();
        consultationRepository = new ConsultationRepository(session);
        try {
            consultation = consultationRepository.findById(id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return consultation;
    }

    public List<Consultation> getAllConsultations() {
        List<Consultation> consultations = null;
        session = _sessionFactory.openSession();
        try {
            consultations = consultationRepository.findAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
        return consultations;

    }
}
