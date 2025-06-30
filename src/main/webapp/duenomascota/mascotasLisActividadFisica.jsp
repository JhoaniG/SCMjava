<%-- 
    Document   : mascotasLisActividadFisica
    Created on : Jun 29, 2025, 8:56:21 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mis Mascotas - Actividad Física</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylos.css">
</head>
<body>

<%@ include file="/duenomascota/menu.jsp" %>
<%@ include file="/duenomascota/barralatereal.jsp" %>

<main style="margin-top: 90px; margin-left: 250px; padding: 20px;">
    <div class="container">
        <h2 class="mb-4 text-primary">🐾 Mis Mascotas para Actividad Física</h2>

        <c:choose>
            <c:when test="${empty listaMascotas}">
                <div class="alert alert-warning">No tienes mascotas registradas.</div>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <c:forEach var="m" items="${listaMascotas}">
                        <div class="col-md-4 mb-4">
                            <div class="card shadow border-0">
                                <div class="card-body">
                                    <h5 class="card-title text-dark">🐶 ${m.nombre}</h5>
                                    <p class="card-text text-dark"><strong>Género:</strong> ${m.genero}</p>
                                    <a href="ActividadFisicaController?accion=ConsultarActividadesMascota&idM=${m.idM}" class="btn btn-primary">
                                        💪 Consultar Actividad Física
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>