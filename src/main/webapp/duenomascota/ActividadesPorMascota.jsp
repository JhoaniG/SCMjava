<%-- 
    Document   : ActividadesPorMascota
    Created on : Jun 29, 2025, 8:57:20 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SCM - Consulta de Actividad Física</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylos.css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylos.css">
    <%-- <link rel="stylesheet" href="../stylos/stylos.css"> --%> <%-- Esta ruta puede ser redundante o incorrecta --%>

    <link href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.css" rel="stylesheet">
</head>
<body>

    <%@include file="/duenomascota/menu.jsp" %>

<div class="container text-center mt-5">
  <h1 class="header-title">SCM - Consulta de Actividad Física</h1>
  <p class="lead">Consulta la actividad física recomendada para tu mascota</p>
</div>

<div class="page-cards-container">
  <c:choose>
    <c:when test="${empty actividades}">
      <div class="alert alert-warning w-100 text-center">
        Esta mascota no tiene actividades físicas registradas.
      </div>
    </c:when>

    <c:otherwise>
      <c:forEach var="af" items="${actividades}">
        <div class="page-card">
          <img src="Imagenes/gato.jpg" alt="Actividad de ${af.nombreMascota}">
          <div class="page-card-body">
            <h4>${af.tipoActividad}</h4>
            <p>${af.descripcion}</p>
            <small class="text-muted d-block">🐾 Mascota: ${af.nombreMascota}</small>
            <small class="text-muted d-block">👩‍⚕️ Vet.: Dr.&nbsp;${af.nombreVeterinario}</small>
            <a href="#" class="btn mt-2">Ver más</a>
          </div>
        </div>
      </c:forEach>
    </c:otherwise>
  </c:choose>
</div>

<div class="container mt-5">
  <div id="calendar"></div>
</div>

<footer class="footer mt-5">
  <div class="container py-4">
    <div class="row text-center text-md-start">
      <div class="col-md-4 mb-3">
        <h5 class="footer-title">John&nbsp;Alvarado</h5>
        <ul class="list-unstyled">
          <li><i class="fas fa-envelope"></i> <a href="mailto:jhonyse2345@gmail.com" class="footer-link">jhonyse2345@gmail.com</a></li>
          <li><i class="fas fa-phone"></i> <span class="footer-text">321‑613‑2975</span></li>
        </ul>
      </div>
      <div class="col-md-4 mb-3">
        <h5 class="footer-title">Leonardo&nbsp;Atencio</h5>
        <ul class="list-unstyled">
          <li><i class="fas fa-envelope"></i> <a href="mailto:leonardoatenciob30@gmail.com" class="footer-link">leonardoatenciob30@gmail.com</a></li>
          <li><i class="fas fa-phone"></i> <span class="footer-text">321‑960‑9864</span></li>
        </ul>
      </div>
      <div class="col-md-4 mb-3">
        <h5 class="footer-title">Jhoani&nbsp;Gallo</h5>
        <ul class="list-unstyled">
          <li><i class="fas fa-envelope"></i> <a href="mailto:jhoanigallo@gmail.com" class="footer-link">jhoanigallo@gmail.com</a></li>
          <li><i class="fas fa-phone"></i> <span class="footer-text">312‑311‑0320</span></li>
        </ul>
      </div>
    </div>
    <div class="text-center mt-4">
      <p class="footer-copy">&copy; 2024. Todos los derechos reservados.</p>
    </div>
  </div>
</footer>

<%@include file="/duenomascota/barralatereal.jsp" %>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

<script>
  document.addEventListener('DOMContentLoaded', function () {
    const calendarEl = document.getElementById('calendar');

    // ======== FECHA ACTUAL EN FORMATO YYYY-MM-DD ========
    const today = new Date().toISOString().split('T')[0];

    const calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: 'dayGridMonth',
      locale: 'es',
      selectable: true,
      editable: false,

      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },

      // Bloquea cualquier fecha anterior a hoy
      validRange: { start: today },

      dateClick: function (info) {
        // Evita doble validación (por si cambian validRange más adelante)
        if (info.dateStr < today) {
          alert('No puedes programar actividades físicas en fechas pasadas.');
          return;
        }

        const activityEvent = prompt('Ingrese la actividad física para el ' + info.dateStr);
        if (activityEvent) {
          calendar.addEvent({
            title: activityEvent,
            start: info.dateStr,
            allDay: true
          });
          alert('Actividad física añadida para el ' + info.dateStr);
        }
      }
    });

    calendar.render();
  });
</script>
</body>
</html>