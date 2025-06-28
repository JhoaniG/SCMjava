<%-- 
    Document   : listaDiagnosticos
    Created on : 27/06/2025, 9:19:08?a. m.
    Author     : jhoan
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Lista de Diagnósticos</title>
  <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
    crossorigin="anonymous"
  >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylos.css">
</head>
<body>

  <%@ include file="/veterinario/menuV.jsp" %>

  <div class="capa container mt-5">
    <h2 class="text-center mb-4 text-primary">Diagnósticos de Dueños</h2>

    <div class="table-responsive shadow-sm">
      <table class="table table-striped table-hover border border-primary">
        <thead class="table-dark text-center">
          <tr>
            <th>ID Diagnóstico</th>
            <th>Mascota</th>
            <th>Dueño</th>
            <th>Fecha</th>
            <th>Observaciones</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="d" items="${listaDiagnosticos}">
    <tr class="text-center">
      <td>${d.idD}</td>
      <td>${d.nombreM}</td>
      <td>${d.nombreDueno}</td>
      <td>${d.fechaDiagnostico}</td>
      <td>${d.observaciones}</td>
      <td>
        <button class="btn btn-sm btn-primary"
        data-bs-toggle="modal"
        data-bs-target="#citaModal"
        data-idd="${d.idD}">
  Crear Cita
</button>
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
  <%@ include file="/modal/crearCita.jsp" %>

  <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"
  ></script>
  
</body>
</html>
