<%@ page import="org.example.tp_j2ee.model.Patient" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<jsp:useBean id="patients" type="java.util.ArrayList<org.example.tp_j2ee.model.Patient>" scope="request" />
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>TP J2EE - Hopital</title>
    <%@ include file="/WEB-INF/sharedTemplates/importBootstrap.html" %>
</head>
<body>

<%@ include file="/WEB-INF/sharedTemplates/header.html" %>

<!-- Formulaire de recherche -->
<div class="container mt-5">
    <h2 class="mb-4">Formulaire de recherche</h2>
    <form>
        <div class="form-group">
            <label for="search">Recherche</label>
            <input type="text" class="form-control" id="search" placeholder="Entrez votre recherche">
        </div>
        <button type="submit" class="btn btn-primary">Valider</button>
    </form>
</div>

<hr>


<!-- Se Connecter / Ajouter Patient -->
    <!-- get Session and attributes -->
<%
    HttpSession sessionlog = request.getSession(true);
    Boolean isLogged = (sessionlog != null && sessionlog.getAttribute("isLogged") != null && (Boolean) sessionlog.getAttribute("isLogged"));
%>


<div class="container mt-5 flex-column">
    <h2 class="mb-4">Ajouter un patient</h2>

    <!-- Se Connecter -->
    <% if (isLogged) { %>
    <div class="container">
        <div class="my-3 row">
            <div class="col-8 offset-2 p-3 rounded text-bg-dark">
                <form action="add" method="post">
                    <div class="mb-3">
                        <label for="name" class="form-label">Nom:</label>
                        <input type="text" name="name" id="name" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="telephon" class="form-label">Telephone:</label>
                        <input type="text" name="telephon" id="telephon" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="photoRelativePath" class="form-label">Date de Naissance:</label>
                        <input type="date" name="birthDate" id="photoRelativePath" class="form-control">
                    </div>
                    <!-- Add file import -->
                    <hr>
                    <div class="text-end">
                        <button class="btn btn-outline-success"><i class="bi bi-plus-circle"></i> Ajouter Patient</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Ajouter Patient -->
    <% } else { %>
    <a href="${pageContext.request.contextPath}/hospital/connect" class="btn btn-outline-secondary">
        <i class="bi bi-arrow-counterclockwise"></i>
        Se Connecter
    </a>
    <% }  %>
</div>

<hr>

<% if (!patients.isEmpty()) { %>
<div class="container mt-4">
    <% for (Patient patient : patients) {%>
    <div class="card">
        <div class="card-body">
            <p class="card-text">Nom: <%= patient.getName() %></p>
            <p class="card-text">Téléphone <%= patient.getTelephone() %></p>
            <a href="${pageContext.request.contextPath}/hospital/detail?id=<%= patient.getId() %>"
               class="btn btn-primary">Détail</a>
        </div>
    </div>
    <%  }  %>
</div>
<%  }  %>

<!-- Footer -->
<%@ include file="/WEB-INF/sharedTemplates/footer.html" %>

</body>
</html>