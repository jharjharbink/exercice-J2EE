package org.example.tp_j2ee.service;

import org.example.tp_j2ee.model.Patient;
import org.example.tp_j2ee.repository.PatientRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PatientService  {
    private PatientRepository patientRepository;
    private SessionFactory _sessionFactory;
    private Session session;

    public PatientService(SessionFactory sessionFactory){
        _sessionFactory = sessionFactory;
    }

    public Patient getPatient(int id){
        Patient patient = null;
        session = _sessionFactory.openSession();
        patientRepository = new PatientRepository(session);
        try {
            patient = patientRepository.findById(id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return patient;
    }

    public boolean createPatient(String name, String breed, String photoRelativePath){
        boolean result = false;
        session = _sessionFactory.openSession();
        session.beginTransaction();
        patientRepository= new PatientRepository(session);
        Patient patient = new Patient(name, breed, photoRelativePath);
        try {
            patientRepository.create(patient);
            session.getTransaction().commit();
            result = true;
        }catch (Exception except){
            try {
                session.getTransaction().rollback();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }finally {
            session.close();
        }
        return result;
    }

    public List<Patient> getPatients(){
        List<Patient> patients = null;
        session = _sessionFactory.openSession();
        patientRepository = new PatientRepository(session);
        try {
            patients = patientRepository.findAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
        return patients;
    }

    public boolean updatePatient(Patient patient) {
        boolean result = false;
        session = _sessionFactory.openSession();
        patientRepository = new PatientRepository(session);
        try{
            patientRepository.update(patient);
            session.getTransaction().commit();
            result = true;
        } catch (Exception except){
            try {
                session.getTransaction().rollback();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } finally {
            session.close();
        }
        return result;
    }
}
