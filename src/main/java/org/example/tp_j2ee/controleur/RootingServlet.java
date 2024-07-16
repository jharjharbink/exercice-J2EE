package org.example.tp_j2ee.controleur;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.tp_j2ee.model.Consultation;
import org.example.tp_j2ee.model.Patient;
import org.example.tp_j2ee.service.ConsultationService;
import org.example.tp_j2ee.service.PatientService;
import org.example.tp_j2ee.util.HibernateSession;

@WebServlet(name = "rooting", value = "/hospital/*")
public class RootingServlet extends HttpServlet {

    private PatientService patientService;
    private ConsultationService consultationService;

    public void init(){
        patientService = new PatientService(HibernateSession.getSessionFactory());
        consultationService = new ConsultationService(HibernateSession.getSessionFactory());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getPathInfo().substring(1);

        switch (action){
            case "reception":
                showReception(request,response);
                break;
            case "patient-list":
                showPatientList(request,response);
                break;
            case "connect":
                connect(request,response);
                break;
            case "add":
                addPatient(request,response);
                break;
            case "detail":
                showDetails(request,response);
                break;
            case "new-consultation":
                showNewConsultation(request,response);
                break;
            case "addConsultation":
                submitConsultation(request, response);
/*
            case "old-consultation":    // TODO add code when user click on old consultation
                showOldConsultation(request, response):
*/
        }
    }

    private void submitConsultation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String prescription = request.getParameter("prescription");
        String careSheet = request.getParameter("careSheet");
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        Patient patient = patientService.getPatient(patientId);

        Consultation consultation = new Consultation(LocalDate.now(), "Docteur Maboule", prescription, careSheet);
        consultation.setPatient(patient);

        patient.getConsultations().add(consultation);
        patientService.updatePatient(patient);
        request.getRequestDispatcher("/WEB-INF/hospital/patient-list.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doGet(request, response);
    }

    private void showReception(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
    }

    private void showPatientList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Check if patient exists
        List<Patient> patients = patientService.getPatients();
        request.setAttribute("patients", patients);

        // Check if user is logged
        boolean logged = session.getAttribute("isLogged") != null && (boolean) session.getAttribute("isLogged");
        session.setAttribute("isLogged",logged);

        request.getRequestDispatcher("/WEB-INF/hospital/patient-list.jsp").forward(request,response);
    }

    private void connect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Log user
        HttpSession session = request.getSession();
        session.setAttribute("isLogged",true);
        showPatientList(request, response);
    }

    private void addPatient(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String telephon = request.getParameter("telephon");
        String photoRelativePath = request.getParameter("photoRelativePath");
        patientService.createPatient(name, telephon, photoRelativePath);
        showPatientList(request, response);
    }

    private void showDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int patientId = Integer.parseInt(request.getParameter("id"));
        Patient patient = patientService.getPatient(patientId);
        String patientName = patient.getName();
        String patientTel = patient.getTelephone();

        request.setAttribute("patientId", patientId);
        request.setAttribute("patientName", patientName);
        request.setAttribute("patientTel", (patientTel == null) ? "0686109143" : patientTel);   // TODO remove wrong Patient in database
        request.setAttribute("consultations", new ArrayList<Consultation>()); // TODO make consultation

        request.getRequestDispatcher("/WEB-INF/hospital/detail.jsp").forward(request,response);
    }

    private void showNewConsultation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int patientId = Integer.parseInt(request.getParameter("id"));
        Patient patient = patientService.getPatient(patientId);

        String patientName = patient.getName();
        List<Consultation> consultations = patient.getConsultations();
        int patientConsultationNbr = (consultations == null || consultations.isEmpty()) ? 1 : patient.getConsultations().size() + 1;

        request.setAttribute("patientId", patientId);
        request.setAttribute("patientName", patientName);
        request.setAttribute("consultationDate", LocalDate.now());
        request.setAttribute("patientConsultationNbr", patientConsultationNbr);
        request.getRequestDispatcher("/WEB-INF/hospital/consultation.jsp").forward(request,response);
    }


}