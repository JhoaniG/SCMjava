<%-- 
    Document   : dietasPorMascota
    Created on : 29/06/2025, 6:36:23 p. m.
    Author     : jhoan
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dietas de Mascota</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylos.css">
</head>
<body>

<%@ include file="/duenomascota/menu.jsp" %>
<%@ include file="/duenomascota/barralatereal.jsp" %>

<main style="margin-left: 250px; margin-top: 90px; padding: 20px;">
    <div class="container">
        <h2 class="mb-4 text-primary">🍽️ Dietas Asignadas</h2>

        <c:choose>
            <c:when test="${empty dietas}">
                <div class="alert alert-warning">Esta mascota no tiene dietas registradas.</div>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <c:forEach var="dieta" items="${dietas}">
                        <div class="col-md-6 mb-4">
                            <div class="card shadow border-0">
                                <div class="card-body">
                                    <h5 class="card-title">🐾 Mascota: ${dieta.nombreMascota}</h5>
                                    <p class="card-text"><strong>Tipo de Dieta:</strong> ${dieta.tipoDieta}</p>
                                    <p class="card-text"><strong>Descripción:</strong> ${dieta.descripcion}</p>
                                    <p class="card-text"><strong>Veterinario:</strong> Dr. ${dieta.nombreVeterinario}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>

        <a href="DietaController?accion=ConsultarMascotas" class="btn btn-secondary mt-4">← Volver a Mis Mascotas</a>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
