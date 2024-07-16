<%@ page import="java.time.LocalDate" %><%--
  Created by IntelliJ IDEA.
  User: Administrateur
  Date: 16/07/2024
  Time: 14:41
  To change this template use File | Settings | File Templates.


<jsp:useBean id="patient" type="org.example.tp_j2ee.model.Patient" scope="request" />
--%>
<jsp:useBean id="patientName" type="java.lang.String" scope="request" />
<jsp:useBean id="patientConsultationNbr" type="java.lang.String" scope="request" />
<jsp:useBean id="consultationDate" type="java.time.LocalDate" scope="request" />



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>TP J2EE - Hopital</title>
    <%@ include file="/WEB-INF/sharedTemplates/importBootstrap.html" %>
</head>
<body>

<!-- Header -->
<%@ include file="/WEB-INF/sharedTemplates/header.html" %>

<h2>Patient: <%= patientName %> , consultation ref <%=patientConsultationNbr%> , Date: <%=LocalDate.now()%><h2>

<div class="container mt-4">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Ajouter une Consultation</h5>
            <form action="addConsultation" method="POST">
                <div class="mb-3">
                    <label for="description" class="form-label">Description</label>
                    <textarea class="form-control" id="description" name="description" rows="5" placeholder="Entrez la description ici..."></textarea>
                </div>
                <div class="mb-3">
                    <label for="prescription" class="form-label">Prescription</label>
                    <textarea class="form-control" id="prescription" name="prescription" rows="5" placeholder="Entrez la prescription ici..."></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Valider</button>
            </form>
        </div>
    </div>
</div>


<!-- Footer -->
<%@ include file="/WEB-INF/sharedTemplates/footer.html" %>

</body>
</html>