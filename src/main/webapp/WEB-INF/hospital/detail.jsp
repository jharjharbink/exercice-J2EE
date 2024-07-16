<%@ page import="org.example.tp_j2ee.model.Consultation" %>
<%@ page import="java.time.LocalDate" %><%--
  Created by IntelliJ IDEA.
  User: Administrateur
  Date: 16/07/2024
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="patientId" type="java.lang.Integer" scope="request" />
<jsp:useBean id="patientName" type="java.lang.String" scope="request" />
<jsp:useBean id="patientTel" type="java.lang.String" scope="request" />
<jsp:useBean id="consultations" type="java.util.ArrayList<org.example.tp_j2ee.model.Consultation>" scope="request" />
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

<h2>Info du patient</h2>
<!-- insert here code for image -->

<div class="container">
    <div>Nom: <%= patientName %> </div>
</div>
<div class="container">
    <div>Numéro de tel: <%= patientTel %> </div>
</div>

<div class="container flex-column">
    <h2>Ajouter une consultation: </h2>
    <a href="${pageContext.request.contextPath}/hospital/new-consultation?id=<%= patientId %>"
       class="btn btn-primary">Ajouter Consultation</a>
</div>

<% if (consultations.isEmpty()) { %>
    <p>Le patient n'a pas de consultation</p>
<% } else { %>
    <div class="container flex-line">
        <% for (Consultation consultation : consultations) {%>
            <div>ref: <%= consultation.getId()%> date: <%=consultation.getDate()%></div>
            <a href="${pageContext.request.contextPath}/hospital/old-consultation?id=<%= consultation.getId() %>">
                Détail de la consultation</a>
        <%  }  %>
    </div>
<% }  %>





<!-- Footer -->
<%@ include file="/WEB-INF/sharedTemplates/footer.html" %>

</body>
</html>
