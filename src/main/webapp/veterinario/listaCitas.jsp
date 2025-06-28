<%-- 
    Document   : Citas
    Created on : 28/06/2025, 9:05:06 a. m.
    Author     : jhoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/veterinario/menuV.jsp" %>
<!DOCTYPE html>
<html lang="es">
    <head>
       <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylos.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"> 
        
        
        
    </head>
<div class="capa container mt-5">
  <h2 class="text-center mb-4 text-primary">Citas Asignadas</h2>

  <div class="table-responsive shadow-sm">
    <table class="table table-striped table-hover border border-primary">
      <thead class="table-dark text-center">
        <tr>
          <th>#</th>
          <th>Mascota</th>
          <th>Dueño</th>
          <th>Fecha</th>
          <th>Motivo</th>
          <th>Estado</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="cita" items="${listaCitas}" varStatus="status">
          <tr class="text-center">
            <td>${status.index + 1}</td>
            <td>${cita.nombreMascota}</td>
            <td>${cita.nombreDueno}</td>
            <td>${cita.fechaCita}</td>
            <td>${cita.motivoCita}</td>
            <td>
              <span class="badge 
                <c:choose>
                  <c:when test="${cita.estadoCita eq 'Pendiente'}">bg-warning</c:when>
                  <c:when test="${cita.estadoCita eq 'Confirmada'}">bg-success</c:when>
                  <c:otherwise>bg-secondary</c:otherwise>
                </c:choose>">
                ${cita.estadoCita}
              </span>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>

  <div class="text-center mt-4">
    <a href="veterinario/veterinario.jsp" class="btn btn-outline-secondary">
      <i class="fas fa-arrow-left me-1"></i> Volver al Panel
    </a>
  </div>
</div>

<%@ include file="/veterinario/barralateralV.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>